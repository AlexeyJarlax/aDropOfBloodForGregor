package com.pavlovalexey.adropofbloodforgregor

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.pavlovalexey.adropofbloodforgregor.nav.NavGraph
import com.pavlovalexey.adropofbloodforgregor.ui.theme.MyTheme
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.statusBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            MyTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    activity = this,
                    modifier = Modifier.fillMaxSize(),
                    intent = intent
                )
            }
        }
    }
}