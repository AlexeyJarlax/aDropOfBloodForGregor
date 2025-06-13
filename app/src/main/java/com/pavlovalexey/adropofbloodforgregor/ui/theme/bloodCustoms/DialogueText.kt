package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1

@Composable
fun DialogueText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        color = text1
    )
}

@Composable
fun DialogueBoldText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
//        fontWeight = FontWeight.Bold,
        modifier = modifier,
        fontSize = 36.sp,
        color = text1
    )
}







