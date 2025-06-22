package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pavlovalexey.adropofbloodforgregor.ui.theme.BodyMedium_14_Regular
import com.pavlovalexey.adropofbloodforgregor.ui.theme.HeadlineSmall_24_Regular
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red300
import com.pavlovalexey.adropofbloodforgregor.ui.theme.Red700
import com.pavlovalexey.adropofbloodforgregor.ui.theme.TitleSmall_14_Medium
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1

val dark = Red700
val bright = text1

@Composable
fun CustomStyledDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = dark,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = bright,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
fun MyStyledDialogWithTitle(
    onDismissRequest: () -> Unit,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    gap: Int = 10
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = dark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = gap.dp)
                    .border(
                        width = 2.dp,
                        color = bright,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    content()
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(dark)
                    .padding(horizontal = 8.dp)

            ) {
                title()
            }
        }
    }
}

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    confirmButtonText: String = "Подтвердить",
    dismissButtonText: String = "Отменить",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    CustomStyledDialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(title)

            Spacer(modifier = Modifier.height(8.dp))

            Text(message, style = MaterialTheme.typography.BodyMedium_14_Regular, color = bright)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = onDismiss) {
                    Text(
                        text = dismissButtonText,
                        color = bright
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onConfirm) {
                    Text(
                        text = confirmButtonText,
                        color = bright)
                }
            }
        }
    }
}

@Composable
fun MessageDialog(
    title: String,
    message: String,
    buttonText: String = "Закрыть",
    onButtonClick: () -> Unit
) {
    CustomStyledDialog(onDismissRequest = onButtonClick) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, style = MaterialTheme.typography.TitleSmall_14_Medium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(message, style = MaterialTheme.typography.HeadlineSmall_24_Regular)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onButtonClick) {
                Text(buttonText)
            }
        }
    }
}