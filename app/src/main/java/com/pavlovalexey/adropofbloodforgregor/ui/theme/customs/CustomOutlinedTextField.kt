package com.pavlovalexey.adropofbloodforgregor.ui.theme.customs

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.ui.theme.My1
import com.pavlovalexey.adropofbloodforgregor.ui.theme.My3
import com.pavlovalexey.adropofbloodforgregor.ui.theme.My7

class FallingStarsTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = "*".repeat(text.text.length)
        return TransformedText(
            AnnotatedString(transformedText),
            OffsetMapping.Identity
        )
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError: Boolean = false,
    singleLine: Boolean = true,
    backgroundColor: Color,
    isPassword: Boolean,
    keyboardActions: () -> Unit,
) {
    val transformation = remember { FallingStarsTransformation() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue -> onValueChange(newValue) },
            label = {
                Text(
                    text = label,
                    color = if (isError) Color.Red else My7
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = My3.copy(alpha = 0.6f),
                    textAlign = TextAlign.Start
                )
            },
            isError = isError,
            singleLine = singleLine,
            visualTransformation = transformation,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardActions() }
            ),

            // Здесь обязательно вызываем OutlinedTextFieldDefaults.colors(...)
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = My3,
//                unfocusedTextColor = My3,
//                disabledTextColor = My3.copy(alpha = 0.3f),
//                errorTextColor = Color.Red,
//                focusedContainerColor = backgroundColor,
//                unfocusedContainerColor = backgroundColor,
//                disabledContainerColor = backgroundColor.copy(alpha = 0.3f),
//                errorContainerColor = backgroundColor,
//                cursorColor = My7,
//                errorCursorColor = Color.Red,
//                focusedIndicatorColor = My7,
//                unfocusedIndicatorColor = My1,
//                disabledIndicatorColor = My1.copy(alpha = 0.3f),
//                errorIndicatorColor = Color.Red,
//                focusedLabelColor = My7,
//                unfocusedLabelColor = My3,
//                disabledLabelColor = My3.copy(alpha = 0.3f),
//                errorLabelColor = Color.Red,
//                focusedPlaceholderColor = My3.copy(alpha = 0.6f),
//                unfocusedPlaceholderColor = My3.copy(alpha = 0.6f),
//                disabledPlaceholderColor = My3.copy(alpha = 0.3f),
//                errorPlaceholderColor = Color.Red,
//                focusedSupportingTextColor = My3,
//                unfocusedSupportingTextColor = My3,
//                disabledSupportingTextColor = My3.copy(alpha = 0.3f),
//                errorSupportingTextColor = Color.Red,
//                focusedLeadingIconColor = My7,
//                unfocusedLeadingIconColor = My3,
//                disabledLeadingIconColor = My3.copy(alpha = 0.3f),
//                errorLeadingIconColor = Color.Red,
//                focusedTrailingIconColor = My7,
//                unfocusedTrailingIconColor = My3,
//                disabledTrailingIconColor = My3.copy(alpha = 0.3f),
//                errorTrailingIconColor = Color.Red,
//                focusedPrefixColor = My3,
//                unfocusedPrefixColor = My3,
//                disabledPrefixColor = My3.copy(alpha = 0.3f),
//                errorPrefixColor = Color.Red,
//                focusedSuffixColor = My3,
//                unfocusedSuffixColor = My3,
//                disabledSuffixColor = My3.copy(alpha = 0.3f),
//                errorSuffixColor = Color.Red
//            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}
