package com.pavlovalexey.adropofbloodforgregor.screens.character

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Gray400
import com.pavlovalexey.adropofbloodforgregor.ui.theme.LabelLarge_14_Medium
import com.pavlovalexey.adropofbloodforgregor.ui.theme.TitleLarge_22_Regular
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2NotActive

@Composable
fun CharacterCard(
    name: String,
    imageRes: Int,
    progress: String,
    isColored: Boolean,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
) {
    val colorFilter = if (!isColored) {
        val matrix = ColorMatrix().apply { setToSaturation(0f) }
        ColorFilter.colorMatrix(matrix)
    } else {
        null
    }

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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (isColored) {
                Text(
                    text = name,
                    style = TitleLarge_22_Regular,
                    color = text1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = progress,
                    style = LabelLarge_14_Medium,
                    color = text1,
                    modifier = Modifier.padding(start = 16.dp)
                )
            } else {
                Text(
                    text = name,
                    style = TitleLarge_22_Regular,
                    color = text2NotActive
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = progress,
                    style = LabelLarge_14_Medium,
                    color = text2NotActive,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}