package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pavlovalexey.adropofbloodforgregor.ui.theme.*

@Composable
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Индикатор прогресса истории:",
            style = TitleLarge_22_Regular,
            color = text1
        )
        Spacer(modifier = Modifier.height(16.dp))
        LegendItem(
            color = VioletBlue600,
            description = "Пройденная глава"
        )
        Spacer(modifier = Modifier.height(8.dp))
        LegendItem(
            color = Orange600,
            description = "Есть открытая глава"
        )
        Spacer(modifier = Modifier.height(8.dp))
        LegendItem(
            color = Gray500,
            description = "Глава недоступна"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Обратите внимание, что сюжеты персонажей взаимосвязаны и некоторые главы одного персонажа открываются при прохождении глав другого.",
            style = BodyMedium_14_Regular,
            color = text2NotActive
        )
    }
}

@Composable
private fun LegendItem(color: Color, description: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = description,
            style = BodyMedium_14_Regular,
            color = text1
        )
    }
}
