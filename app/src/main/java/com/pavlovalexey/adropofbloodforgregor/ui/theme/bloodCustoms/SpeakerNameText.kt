package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2



@Composable
fun SpeakerNameText(text: String) {
    Text(
        text = text.uppercase(),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = text2
    )
}