package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

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
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.Dp
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.data.Speaker

/**
 * CharacterOverlay – отображает спрайт персонажа над сценой.
 * Нижняя часть картинки «приклеивается» к нижней части экрана.
 */
@Composable
fun CharacterOverlay(speaker: Speaker, modifier: Modifier) {
    val imageRes = when (speaker) {
        Speaker.GREGOR -> R.drawable.model1_3
        Speaker.LILIAN -> R.drawable.model1_1
        Speaker.ASTRA -> R.drawable.model1_2
        Speaker.BERNARD -> R.drawable.model1_4
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
                    .wrapContentWidth(),
                contentScale = ContentScale.Fit
            )
        }
    }
}