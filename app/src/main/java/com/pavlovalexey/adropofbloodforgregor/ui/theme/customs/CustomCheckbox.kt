package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.pavlovalexey.adropofbloodforgregor.ui.theme.*

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean
) {
    val checkboxColors = CheckboxDefaults.colors(
        checkedColor = VioletBlue600,
        uncheckedColor = Gray200,
        checkmarkColor = White,
        disabledCheckedColor = VioletBlue600.copy(alpha = 0.3f),
        disabledUncheckedColor = Gray200.copy(alpha = 0.3f)
    )

    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = checkboxColors,
        enabled = enabled
    )
}

@Preview
@Composable
fun PreviewCustomCheckbox() {
    var checked by remember { mutableStateOf(false) }

    CustomCheckbox(
        checked = checked,
        onCheckedChange = { checked = it },
        enabled = true
    )
}
