package com.pavlovalexey.adropofbloodforgregor

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.pavlovalexey.adropofbloodforgregor.nav.NavGraph
import com.pavlovalexey.adropofbloodforgregor.ui.theme.MyTheme
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2
import com.pavlovalexey.adropofbloodforgregor.utils.MediaPlayerManager
import com.pavlovalexey.adropofbloodforgregor.utils.SteosTtsManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var mediaPlayerManager: MediaPlayerManager
    @Inject lateinit var ttsManager: SteosTtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            MyTheme {
                LaunchedEffect(Unit) {
                    mediaPlayerManager.initialize(R.raw.background_music)
                }

                var musicOn by rememberSaveable { mutableStateOf(mediaPlayerManager.isMusicOn) }

                LaunchedEffect(musicOn) {
                    if (musicOn) mediaPlayerManager.play()
                    else          mediaPlayerManager.pause()
                }

                val navController = rememberNavController()

                Box(Modifier.fillMaxSize()) {
                    NavGraph(
                        navController = navController,
                        activity = this@MainActivity,
                        modifier = Modifier.fillMaxSize(),
                        intent = intent,
                        ttsManager = ttsManager
                    )

                    IconButton(
                        onClick = { musicOn = mediaPlayerManager.toggle() },
                        modifier = Modifier
                            .padding(6.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = if (musicOn) Icons.Filled.MusicNote else Icons.Filled.MusicOff,
                            contentDescription = if (musicOn) "Отключить музыку" else "Включить музыку",
                            tint = text2
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerManager.release()
    }
}