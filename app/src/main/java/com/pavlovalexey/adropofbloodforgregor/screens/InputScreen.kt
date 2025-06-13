package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.data.CharacterStats
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel

@Composable
fun InputScreen(
    onExit: () -> Unit,
    viewModel: GameViewModel,
) {
    val resources by viewModel.resources.collectAsState()
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0x80000000))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Ресурсы", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))

                LazyColumn {
                    item {
                        Text("Вино: ${resources.wine}")
                        Spacer(Modifier.height(8.dp))
                    }
                    val list = listOf(
                        "Лилиан"  to resources.lilian,
                        "Бернард" to resources.bernard,
                        "Грегор"  to resources.gregor,
                        "Астра"   to resources.astra,
                    )
                    items(list) { (name, stats) ->
                        ResourceRow(name, stats)
                        Spacer(Modifier.height(8.dp))
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                        CustomButtonOne(
                            onClick = onExit,
                            text = "Закрыть"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ResourceRow(name: String, stats: CharacterStats) {
    Text(
        "$name — " +
                "HP ${stats.health.toInt()}, " +
                "Голод ${stats.hunger.toInt()}, " +
                "Прогресс ${stats.progress.toInt()}%"
    )
}