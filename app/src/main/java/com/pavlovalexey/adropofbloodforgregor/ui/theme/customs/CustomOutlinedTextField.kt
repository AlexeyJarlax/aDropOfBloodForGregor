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

class FallingStarsTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = "*".repeat(text.text.length)
        return TransformedText(
            AnnotatedString(transformedText),
            OffsetMapping.Identity
        )
    }
}

//@Composable
//fun CustomOutlinedTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    placeholder: String,
//    isError: Boolean = false,
//    singleLine: Boolean = true,
//    backgroundColor: Color,
//    isPassword: Boolean,
//    keyboardActions: () -> Unit,
//) {
//    val transformation = remember { FallingStarsTransformation() }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(0.dp)
//    ) {
//        OutlinedTextField(
//            value = value,
//            onValueChange = { newValue -> onValueChange(newValue) },
//            label = {
//                Text(
//                    text = label,
//                    color = if (isError) Color.Red else My7
//                )
//            },
//            placeholder = {
//                Text(
//                    text = placeholder,
//                    color = My3.copy(alpha = 0.6f),
//                    textAlign = TextAlign.Start
//                )
//            },
//            isError = isError,
//            singleLine = singleLine,
//            visualTransformation = transformation,
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = { keyboardActions() }
//            ),
//            shape = RoundedCornerShape(8.dp)
//        )
//    }
//}
