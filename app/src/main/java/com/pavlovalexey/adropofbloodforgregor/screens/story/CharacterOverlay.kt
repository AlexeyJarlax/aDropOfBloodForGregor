package com.pavlovalexey.adropofbloodforgregor.screens.story

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.graphicsLayer
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.data.Speaker

@Composable
fun CharacterOverlay(
    speaker: Speaker,
    modifier: Modifier,
    flipHorizontally: Boolean = false
) {
    speaker.imageRes?.let { resId ->
        val imageHeight = if (speaker == Speaker.GREGOR) {
            400.dp * 1.15f
        } else {
            400.dp * 1.1f
        }

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = "Спрайт персонажа",
                modifier = Modifier
                    .height(imageHeight)
                    .wrapContentWidth()
                    .graphicsLayer {
                        scaleX = if (flipHorizontally) -1f else 1f
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}