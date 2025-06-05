package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Gray100
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Transparent
import com.pavlovalexey.adropofbloodforgregor.ui.theme.White

@Composable
fun CustomMultiCard(
    modifier: Modifier = Modifier,
    expandToFullWidth: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    paddingInternal: Int = 0,
    content: @Composable () -> Unit,
) {
    val cardModifier = if (expandToFullWidth) {
        modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Gray100, shape = shape)
    } else {
        modifier
            .border(width = 1.dp, color = Gray100, shape = shape)
    }

    Spacer(modifier = Modifier.height(26.dp))
    Card(
        modifier = cardModifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = Transparent)
    ) {
        content()
    }
}