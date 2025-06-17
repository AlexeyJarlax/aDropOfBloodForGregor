package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Gray200
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red100
import com.pavlovalexey.adropofbloodforgregor.ui.theme.TitleMedium_16_Medium
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1

/**
 * Кнопка-выбор (вариант) в узле Choice.
 */
@Composable
fun ChoiceButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = text1,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
    ) {
        Text(text = text, color = text1, style = TitleMedium_16_Medium)
    }
}