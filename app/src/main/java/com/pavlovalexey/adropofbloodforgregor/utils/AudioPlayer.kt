package com.pavlovalexey.adropofbloodforgregor.utils

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

object AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun initialize(context: Context, @RawRes musicResId: Int) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId).apply {
                isLooping = true
                start()
            }
        }
    }

    fun play() {
        mediaPlayer?.let {
            if (!it.isPlaying) it.start()
        }
    }

    fun pause() {
        mediaPlayer?.let {
            if (it.isPlaying) it.pause()
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}