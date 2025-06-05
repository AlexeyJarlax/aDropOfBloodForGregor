package com.pavlovalexey.adropofbloodforgregor

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.pavlovalexey.adropofbloodforgregor.nav.NavGraph
import com.pavlovalexey.adropofbloodforgregor.ui.theme.MyTheme
import androidx.navigation.compose.rememberNavController
//import com.pavlov.MyShadowGallery.ui.images.ImagesViewModel
//import kotlin.getValue


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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