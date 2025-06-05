package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

object MatrixAnimationSettings {
    val symbols = listOf(
        "𐌀", "𐌁", "𐌂", "𐌃", "𐌄", "𐌚", "𐌆", "𐌇", "𐌉", "𐌊", "𐌋", "𐌌", "𐌏", "𐌐", "𐌒", "𐌓", "𐌔", "𐌕", "𐌖",
    )

    const val rows = 18
    const val fontSize = 14
    val symbolPadding = 1.dp
    val columnStartDelayRange = 50L..20000L
    const val updateInterval = 250L // Интервал обновления (мс)
    const val fadeSpeed = 0.05f     // Скорость затухания альфы за "тик"
}

data class MatrixSymbol(
    val symbol: String,
    val yPos: Float,
    val xPos: Float,
    val alpha: Float
)

@Composable
fun MatrixBackground(colorComponent: Int = 155) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    val textSizePx = with(density) { MatrixAnimationSettings.fontSize.sp.toPx() }
    val symbolPaddingPx = with(density) { MatrixAnimationSettings.symbolPadding.toPx() }
    val verticalStep = textSizePx + symbolPaddingPx

    val paint = remember {
        android.graphics.Paint().apply {
            isAntiAlias = true
        }
    }
    paint.textSize = textSizePx
    val horizontalSpacing = textSizePx * 1.5f
    val maxColumnsFit = (screenWidthPx / horizontalSpacing).toInt()
    val columnsCount = MatrixAnimationSettings.rows
    val actualColumnsCount = if (columnsCount <= maxColumnsFit) columnsCount else maxColumnsFit
    val availableSlots = (0 until maxColumnsFit).toList().shuffled().take(actualColumnsCount)
    val columnOffsets = remember {
        availableSlots.map { slotIndex ->
            slotIndex * horizontalSpacing
        }
    }

    val columnDelays = remember {
        List(actualColumnsCount) {
            Random.nextLong(MatrixAnimationSettings.columnStartDelayRange.first, MatrixAnimationSettings.columnStartDelayRange.last)
        }
    }

    val columnsState = remember {
        columnOffsets.map { xPos ->
            mutableStateListOf<MatrixSymbol>()
        }.toMutableStateList()
    }

    val columnHeights = remember {
        MutableList(actualColumnsCount) { 0 }
    }

    columnsState.forEachIndexed { columnIndex, column ->
        LaunchedEffect(columnIndex) {
            delay(columnDelays[columnIndex]) // индивидуальная задержка старта
            while (isActive) {
                val nextIndex = columnHeights[columnIndex] + 1
                val yPos = nextIndex * verticalStep
                val newSymbolChar = MatrixAnimationSettings.symbols.random()
                column.add(
                    MatrixSymbol(
                        symbol = newSymbolChar,
                        yPos = yPos,
                        xPos = columnOffsets[columnIndex],
                        alpha = 1f
                    )
                )
                columnHeights[columnIndex] = nextIndex

                val updated = column.mapNotNull { sym ->
                    val newAlpha = sym.alpha - MatrixAnimationSettings.fadeSpeed
                    if (newAlpha > 0f) sym.copy(alpha = newAlpha) else null
                }.toMutableList()

                column.clear()
                column.addAll(updated)

                delay(MatrixAnimationSettings.updateInterval)
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        columnsState.forEach { columnSymbols ->
            for (symbol in columnSymbols) {
                val alphaInt = (symbol.alpha * 255).toInt().coerceIn(0,255)
                paint.color = android.graphics.Color.argb(
                    alphaInt,
                    colorComponent,
                    0,
                    0
                )
                drawContext.canvas.nativeCanvas.drawText(
                    symbol.symbol.toString(),
                    symbol.xPos,
                    symbol.yPos,
                    paint
                )
            }
        }
    }
}
