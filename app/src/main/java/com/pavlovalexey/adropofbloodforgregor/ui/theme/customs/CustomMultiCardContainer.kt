package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Black
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Transparent

@Suppress("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomMultiCardContainer(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
//    extraSpacing: Int = 0,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Transparent)
                .padding(start = 26.dp, end = 26.dp, bottom = 26.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            header()
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                content()
            }
        }
    }
}