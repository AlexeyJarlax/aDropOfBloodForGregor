package com.pavlovalexey.adropofbloodforgregor.utils

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Base64
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.IOException

@Singleton
class SteosTtsManager @Inject constructor(
    private val api: SteosVoiceApi,
    private val mediaPlayerManager: MediaPlayerManager,
    @ApplicationContext private val ctx: Context
) {

    suspend fun speak(text: String, voiceId: Int = VOICE_ID) = coroutineScope {
        val chunks = chunkText(text)
        if (chunks.isEmpty()) return@coroutineScope

        suspend fun fetchFileSafe(chunk: String): File? {
            return try {
                val req = SynthesisRequest(voiceId = voiceId, text = chunk)
                val resp = api.synthesize(TOKEN, req)
                if (!resp.isSuccessful) {
                    val errorMsg = resp.errorBody()?.string() ?: "unknown"
                    Log.e(TAG, "Ошибка синтеза: ${resp.code()} msg=$errorMsg chunk='$chunk'")
                    null
                } else {
                    resp.body()?.let { saveToCache(it.fileContents) }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Исключение при синтезе: ${e.message}")
                null
            }
        }

        var nextFileDeferred = async(Dispatchers.IO) { fetchFileSafe(chunks[0]) }

        for (i in chunks.indices) {
            val file = try {
                nextFileDeferred.await()
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при ожидании файла: ${e.message}")
                null
            }

            if (i + 1 < chunks.size) {
                nextFileDeferred = async(Dispatchers.IO) { fetchFileSafe(chunks[i + 1]) }
            }

            if (file != null && file.exists()) {
                playAndAwait(file)
            } else {
                break
            }
        }
    }

    private fun chunkText(text: String): List<String> {
        val list = mutableListOf<String>()
        var start = 0
        while (start < text.length) {
            val endIdx = (start + MAX_SYMBOLS).coerceAtMost(text.length)
            val splitIdx = if (endIdx < text.length) {
                var lastPos = -1
                DELIMITERS.forEach { delim ->
                    val pos = text.lastIndexOf(delim, endIdx - 1)
                    if (pos >= start && pos > lastPos) lastPos = pos
                }
                if (lastPos >= start) {
                    lastPos + 1
                } else {
                    val lastSpace = text.lastIndexOf(' ', endIdx - 1)
                    if (lastSpace >= start) lastSpace + 1
                    else endIdx
                }
            } else endIdx

            val chunk = text.substring(start, splitIdx).trim()
            if (chunk.isNotEmpty()) list.add(chunk)
            start = splitIdx
        }
        return list
    }

    private suspend fun saveToCache(fileContentsBase64: String): File =
        withContext(Dispatchers.IO) {
            val bytes = Base64.decode(fileContentsBase64, Base64.DEFAULT)
            val file = File(ctx.cacheDir, "tts_\${System.currentTimeMillis()}.wav")
            file.outputStream().use { it.write(bytes) }
            file
        }

    private suspend fun playAndAwait(file: File) =
        withContext(Dispatchers.Main) {
            suspendCancellableCoroutine<Unit> { cont ->
                val mp = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANT)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                            .build()
                    )
                    setDataSource(ctx, Uri.fromFile(file))
                    prepare()
                    setOnCompletionListener {
                        it.release()
                        if (cont.isActive) cont.resume(Unit)
                    }
                    start()
                }
                cont.invokeOnCancellation { mp.release() }
            }
        }
}
