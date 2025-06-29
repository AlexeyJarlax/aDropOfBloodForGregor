package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ConfirmationDialog
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel
import androidx.core.net.toUri
import com.pavlovalexey.adropofbloodforgregor.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    onAboutClicked: () -> Unit,
    onSecuritySettingsClicked: () -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
) {
    var showResetDialog by remember { mutableStateOf(false) }
    val musicVol by remember { derivedStateOf { viewModel.musicVolume } }
    val context = LocalContext.current
    val textSize = viewModel.dialogueTextSize
    val fontIdx = viewModel.dialogueFontIndex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        CustomButtonOne(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data =
                        "https://play.google.com/store/apps/details?id=com.pavlovalexey.adropofbloodforgregor".toUri()
                }
                context.startActivity(intent)
            },
            text = "Страница приложения в GooglePlay"
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomButtonOne(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data =
                        "https://raw.githubusercontent.com/AlexeyJarlax/Privacy-Policy/refs/heads/main/PP1.0.txt".toUri()
                }
                context.startActivity(intent)
            },
            text = "Политика конфиденциальности"
        )
        Spacer(modifier = Modifier.height(8.dp))

        CustomButtonOne(
            onClick = { showResetDialog = true },
            text = "Сбросить прогресс игры"
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Громкость музыки: ${(musicVol * 100).toInt()}%",
            color = text1
        )
        Slider(
            value = musicVol,
            onValueChange = { viewModel.updateMusicVolume(it) },
            valueRange = 0f..1f,
            steps = 9,
            colors = SliderDefaults.colors(
                thumbColor = Red600,
                activeTrackColor = Red600,
                inactiveTrackColor = Red200
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Выбор размера текста: ${textSize}sp",
            fontSize = textSize.sp,
            fontFamily  = DialogueFontFamilies[fontIdx],
            color = text1
        )

        Slider(
            value = textSize.toFloat(),
            onValueChange = { newValue ->
                viewModel.updateDialogueTextSize(newValue.toInt())
            },
            valueRange = 12f..28f,
            steps = 28 - 12 - 1,
            colors = SliderDefaults.colors(
                thumbColor = Red600,
                activeTrackColor = Red600,
                inactiveTrackColor = Red200,
                activeTickColor = Red600,
                inactiveTickColor = Red200
            ),
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Выбор шрифта",
            fontSize = textSize.sp,
            fontFamily  = DialogueFontFamilies[fontIdx],
            color       = text1
        )
        Slider(
            value = fontIdx.toFloat(),
            onValueChange = { viewModel.updateDialogueFontIndex(it.toInt()) },
            valueRange = 0f..DialogueFontFamilies.lastIndex.toFloat(),
            steps = DialogueFontFamilies.lastIndex - 1,
            colors = SliderDefaults.colors(
                thumbColor          = Red600,
                activeTrackColor    = Red600,
                inactiveTrackColor  = Red200,
                activeTickColor     = Red600,
                inactiveTickColor   = Red200
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
    }

    if (showResetDialog) {
        ConfirmationDialog(
            title = "Сброс прогресса игры",
            message = "Вы уверены, что хотите сбросить прогресс по всем персонажам? Это действие нельзя будет отменить.",
            confirmButtonText = "Сбросить",
            dismissButtonText = "Отмена",
            onConfirm = {
                viewModel.resetAllStates()
                showResetDialog = false
            },
            onDismiss = {
                showResetDialog = false
            }
        )
    }
}
