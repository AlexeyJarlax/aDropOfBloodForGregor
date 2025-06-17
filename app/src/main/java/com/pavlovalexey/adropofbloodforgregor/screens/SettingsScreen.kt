package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.data.StoryStart
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ConfirmationDialog
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    onAboutClicked: () -> Unit,
    onSecuritySettingsClicked: () -> Unit,
    viewModel: GameViewModel,
) {

    var showResetDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

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

        CustomButtonOne(
            onClick = { showResetDialog = true },
            text = "Сбросить прогресс игры"
        )
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
