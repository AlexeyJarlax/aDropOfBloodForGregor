package com.pavlovalexey.adropofbloodforgregor.ui.theme

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun dialogueTextStyle(
    sizeSp: Int,
    fontIdx: Int
): TextStyle {
    val family: FontFamily = DialogueFontFamilies.getOrElse(fontIdx) {
        DialogueFontFamilies.first()
    }

    return TextStyle(
        fontFamily    = family,
        fontWeight    = FontWeight.Medium,
        fontSize      = sizeSp.sp,
        lineHeight    = 24.sp,
        letterSpacing = 0.15.sp
    )
}