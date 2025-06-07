package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

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
import com.pavlovalexey.adropofbloodforgregor.vm.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavHostController,
    onAboutClicked: () -> Unit,
    onSecuritySettingsClicked: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val prefs = viewModel.prefs
    var showResetDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
              CustomButtonOne(
            onClick = onAboutClicked,
            text = "О приложении"
        )
        CustomButtonOne(
            onClick = onSecuritySettingsClicked,
            text = "Безопасность"
        )

        Spacer(modifier = Modifier.weight(1f))

        CustomButtonOne(
            onClick = { showResetDialog = true },
            text = "Сбросить прогресс"
        )
    }

    if (showResetDialog) {
        ConfirmationDialog(
            title = "Сброс прогресса",
            message = "Вы уверены, что хотите сбросить прогресс по всем персонажам? Это действие нельзя будет отменить.",
            confirmButtonText = "Да, сбросить",
            dismissButtonText = "Отмена",
            onConfirm = {
                StoryStart.run {
                    prefs.edit()
                        .remove(prefsKeyFor("gregor"))
                        .remove(prefsKeyFor("lilian"))
                        .remove(prefsKeyFor("astra"))
                        .apply()
                }
                showResetDialog = false
            },
            onDismiss = {
                showResetDialog = false
            }
        )
    }
}
