package com.pavlovalexey.adropofbloodforgregor.screens.character

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.*

@Composable
fun CharacterCard(
    name: String,
    charKey: String,
    imageRes: Int,
    isColored: Boolean,
    totalChapters: Int,
    chaptersDone: Set<String>,
    unlockedCount: Int,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
) {
    val colorFilter = if (!isColored) {
        val matrix = ColorMatrix().apply { setToSaturation(0f) }
        ColorFilter.colorMatrix(matrix)
    } else null

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            colorFilter = colorFilter,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = name,
            style = TitleLarge_22_Regular,
            color = if (isColored) text1 else text2NotActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            for (i in totalChapters downTo 1) {
                val id = "${charKey}_chap$i"
                val color = when {
                    chaptersDone.contains(id) -> VioletBlue600
                    i <= unlockedCount        -> Orange600
                    else                      -> Gray500
                }
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}