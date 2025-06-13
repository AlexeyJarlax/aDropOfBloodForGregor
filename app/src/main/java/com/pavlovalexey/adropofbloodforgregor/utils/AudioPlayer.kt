package com.pavlovalexey.adropofbloodforgregor.utils

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.SharedPreferences
import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPlayerManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val prefs: SharedPreferences
) {
    companion object {
        private const val KEY_MUSIC_ON = "music_on"
    }

    private var mediaPlayer: MediaPlayer? = null

    val isMusicOn: Boolean
        get() = prefs.getBoolean(KEY_MUSIC_ON, true)

    fun initialize(@RawRes musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId).apply {
                isLooping = true
                if (isMusicOn) start()
            }
        }
    }

    fun pause() {
        if (isMusicOn) {
            prefs.edit().putBoolean(KEY_MUSIC_ON, false).apply()
            mediaPlayer?.pause()
        }
    }

    fun play() {
        if (!isMusicOn) {
            prefs.edit().putBoolean(KEY_MUSIC_ON, true).apply()
            mediaPlayer?.start()
        }
    }

    fun toggle(): Boolean {
        val newState = !isMusicOn
        prefs.edit().putBoolean(KEY_MUSIC_ON, newState).apply()
        if (newState) mediaPlayer?.start() else mediaPlayer?.pause()
        return newState
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}