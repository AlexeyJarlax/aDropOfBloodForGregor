package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red100
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red400
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red600
import com.pavlovalexey.adropofbloodforgregor.ui.theme.TitleMedium_16_Medium

val on = Red400
val off = Red600
val text = Red100

@Composable
fun CustomRadioButtonOption(
    selected: Boolean,
    title: String,
    subtitle: String,
    price: String,
    icon: ImageVector,
    bubbleText: String? = null,
    bubbleColor: Color = off,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.Top
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = on,
                unselectedColor = off
            ),
        )

        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = title,
                    style = TitleMedium_16_Medium,
                    color = text,
                    modifier = Modifier.weight(1f).padding(start = 12.dp)
                )

                Text(
                    text = price,
                    style = TitleMedium_16_Medium,
                    color = text,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            bubbleText?.let {
                CustomBubbleText(text = bubbleText, bubbleColor = bubbleColor, modifier = Modifier.padding(top = 4.dp, start = 12.dp))
            }

            Text(
                text = subtitle,
                style = TitleMedium_16_Medium,
                color = text,
                modifier = Modifier.padding(top = 4.dp, start = 12.dp)
            )
        }
    }
}