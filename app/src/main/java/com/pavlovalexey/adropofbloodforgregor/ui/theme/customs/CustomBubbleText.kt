package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.BodyMedium_14_Regular
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red400
import com.pavlovalexey.adropofbloodforgregor.ui.theme.White

@Composable
fun CustomBubbleText(
    text: String,
    modifier: Modifier = Modifier,
    bubbleColor: Color = Red400,
) {
    Box(
        modifier = modifier
            .background(bubbleColor, shape = MaterialTheme.shapes.large)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .height(20.dp)
    ) {
        Text(
            text = text,
            style = BodyMedium_14_Regular,
            color = White,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}