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
    val imageRes = when (speaker) {
        Speaker.GREGOR -> R.drawable.model_g
        Speaker.LILIAN -> R.drawable.model1_1
        Speaker.ASTRA -> R.drawable.model1_2
        Speaker.BERNARD -> R.drawable.model_b
        Speaker.NOBODY -> R.drawable.model_0
        Speaker.NARRATOR -> null
    }
    imageRes?.let {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = it),
                contentDescription = "Спрайт персонажа",
                modifier = Modifier
                    .height(400.dp)
                    .wrapContentWidth()
                    .graphicsLayer {
                        scaleX = if (flipHorizontally) -1f else 1f
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}