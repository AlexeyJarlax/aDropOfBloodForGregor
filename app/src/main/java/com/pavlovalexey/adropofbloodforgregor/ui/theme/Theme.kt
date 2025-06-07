package com.pavlovalexey.adropofbloodforgregor.ui.theme

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Моя палитра:
val My3 = Color(0xFFB5E17F) // тексты в полях
val My4 = Color(0xFF000000) // задний фон
val My5 = Color(0xFF35570C)
//val My6 = Color(0xFFFFFFFF)  // текст в кнопках
val My7 = Color(0xFF87E01A) // ProgressIndicator, полоска у ввода, кнопки
val My8 = Color(0xFF1AE0B8)

val Transparent = Color(0x00000000)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val Gray50 = Color(0xFFF8F8F8)
val Gray75 = Color(0xFFF2F4F6)
val Gray100 = Color(0xFFE7E7E8)
val Gray200 = Color(0xFFCED0D2)
val Gray300 = Color(0xFF9DA1A5)
val Gray400 = Color(0xFF6D7178)
val Gray500 = Color(0xFF3C424B)
val Gray600 = Color(0xFF0B131E)

val Blue100 = Color(0xFFECF8FD)
val Blue200 = Color(0xFFC7E9F9)
val Blue300 = Color(0xFF8ED3F2)
val Blue400 = Color(0xFF43B5EA)
val Blue500 = Color(0xFF3691BB)
val Blue600 = Color(0xFF225B75)

val VioletBlue100 = Color(0xFFB3BEF8)
val VioletBlue200 = Color(0xFF8D9EF5)
val VioletBlue300 = Color(0xFF677DF2)
val VioletBlue400 = Color(0xFF415DEE)
val VioletBlue500 = Color(0xFF344ABE)
val VioletBlue600 = Color(0xFF27388F)

val Green100 = Color(0xFFEDFAF1)
val Green200 = Color(0xFFC9EFD6)
val Green300 = Color(0xFF93E0AD)
val Green400 = Color(0xFF4BCB76)
val Green500 = Color(0xFF3CA25E)
val Green600 = Color(0xFF26663B)

val Red100 = Color(0xFFFDECEC)
val Red200 = Color(0xFFF9C7C7)
val Red300 = Color(0xFFF28E8E)
val Red400 = Color(0xFFEA4343)
val Red500 = Color(0xFFBB3636)
val Red600 = Color(0xFF752222)
val Red700 = Color(0xFF4F1717)

val Orange100 = Color(0xFFFDF4EC)
val Orange200 = Color(0xFFF9DFC7)
val Orange300 = Color(0xFFF2BE8E)
val Orange400 = Color(0xFFEA9343)
val Orange500 = Color(0xFFBB7636)
val Orange600 = Color(0xFF754A22)

val text1 = Gray200
val text2 = Red200
val textPressed = Red400

private val DarkColorScheme = darkColorScheme(
    primary = Gray600, // фон кнопок
    onPrimary = Red300,
    secondary = My3,
    onSecondary = My7,
    background = My4,
    surface = My5,
    onBackground = Red300,
    onSurface = Red300
)

@Composable
fun MyTheme(
    customBackgroundColor: Color = DarkColorScheme.background,
    customIconColor: Color = DarkColorScheme.onPrimary,
    content: @Composable () -> Unit
) {
    val customScheme = DarkColorScheme.copy(
        background = customBackgroundColor,
        surface = customBackgroundColor,
        onPrimary = customIconColor,
        onSecondary = customIconColor
    )
    MaterialTheme(
        colorScheme = customScheme,
        typography = AppTypography,
        shapes = Shapes(),
        content = content
    )
}
