package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.My7
import kotlinx.coroutines.delay

@Composable
fun CustomCircularProgressIndicator() {
    val symbols = MatrixAnimationSettings.symbols
    val currentSymbol = remember { mutableStateOf(symbols.random()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentSymbol.value = symbols.random()
            delay(1500L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            color = My7
        )
        Text(
            text = currentSymbol.value.toString(),
            color = My7,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}