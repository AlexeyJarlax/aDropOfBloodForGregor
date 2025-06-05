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
val My1 = Color(0xFF525252) // фон кнопок
val My2 = Color(0xFF000000)
val My3 = Color(0xFFB5E17F) // тексты в полях
val My4 = Color(0xFF000000) // задний фон
val My5 = Color(0xFF35570C)
val My6 = Color(0xFFFFFFFF)  // текст в кнопках
val My7 = Color(0xFF87E01A) // ProgressIndicator, полоска у ввода, кнопки
val My8 = Color(0xFF1AE0B8)
val MyRed = Color(0xFFA10909)

private val DarkColorScheme = darkColorScheme(
    primary = My1,
    onPrimary = My6,
    secondary = My3,
    onSecondary = My7,
    background = My4,
    surface = My5,
    onBackground = My6,
    onSurface = My6
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
