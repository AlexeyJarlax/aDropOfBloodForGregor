package com.pavlovalexey.adropofbloodforgregor.utils

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.SharedPreferences
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPlayerManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val prefs: SharedPreferences
) {

    private var musicPlayer: MediaPlayer? = null
    private var ttsPlayer: MediaPlayer? = null

    val isMusicOn: Boolean
        get() = prefs.getBoolean(KEY_MUSIC_ON, true)

    private var musicVolume: Float = 1f

    fun initialize(@RawRes musicResId: Int) {
        if (musicPlayer == null) {
            musicPlayer = MediaPlayer.create(context, musicResId).apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                isLooping = true
                setVolume(musicVolume, musicVolume)
                if (isMusicOn) start()
            }
        }
    }

    fun pause() {
        if (isMusicOn) {
            prefs.edit().putBoolean(KEY_MUSIC_ON, false).apply()
            musicPlayer?.pause()
        }
    }

    fun play() {
        if (!isMusicOn) {
            prefs.edit().putBoolean(KEY_MUSIC_ON, true).apply()
            musicPlayer?.start()
        }
    }

    fun toggle(): Boolean {
        val newState = !isMusicOn
        prefs.edit().putBoolean(KEY_MUSIC_ON, newState).apply()
        if (newState) musicPlayer?.start() else musicPlayer?.pause()
        return newState
    }

    fun playFromUri(uri: Uri) {
        ttsPlayer?.release()
        ttsPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANT)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            setDataSource(context, uri)
            prepare()
            isLooping = false
            start()
        }
    }

    fun setMusicVolume(volume: Float) {
        musicVolume = volume.coerceIn(0f, 1f)
        musicPlayer?.setVolume(musicVolume, musicVolume)
    }

    fun release() {
        musicPlayer?.release()
        musicPlayer = null
        ttsPlayer?.release()
        ttsPlayer = null
    }
}
