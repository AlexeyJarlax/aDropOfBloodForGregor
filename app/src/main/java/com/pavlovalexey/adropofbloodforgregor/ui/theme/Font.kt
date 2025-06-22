package com.pavlovalexey.adropofbloodforgregor.ui.theme

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.pavlovalexey.adropofbloodforgregor.R

val OldStandardTT = FontFamily(
    Font(R.font.old_standard_tt_regular, weight = FontWeight.Normal),
    Font(R.font.old_standard_tt_bold, weight = FontWeight.Bold),
    Font(
        R.font.old_standard_tt_italic,
        weight = FontWeight.Normal,
        style = androidx.compose.ui.text.font.FontStyle.Italic
    )
)


val Roboto = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal)
)
val BalsamiqSans = FontFamily(
    Font(R.font.balsamiq_sans_regular, weight = FontWeight.Normal)
)
val Lora = FontFamily(
    Font(R.font.lora_regular, weight = FontWeight.Normal)
)

val DialogueFontNames = listOf(
    "Old Standard",
    "Roboto",
    "BalsamiqSans",
    "Lora"
)

val DialogueFontFamilies = listOf(
    OldStandardTT,
    Roboto,
    BalsamiqSans,
    Lora
)