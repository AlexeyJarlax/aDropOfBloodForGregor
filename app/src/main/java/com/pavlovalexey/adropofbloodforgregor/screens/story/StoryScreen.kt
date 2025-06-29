package com.pavlovalexey.adropofbloodforgregor.screens.story

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.data.ChoiceOption
import com.pavlovalexey.adropofbloodforgregor.data.DialogueNode
import com.pavlovalexey.adropofbloodforgregor.data.Speaker
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ConfirmationDialog
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.data.StoryData
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.SceneBackground
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2
import com.pavlovalexey.adropofbloodforgregor.utils.SteosTtsManager
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun StoryScreen(
    onNavigateToCharacter: () -> Unit,
    onNavigateToKeyInput: () -> Unit,
    viewModel: GameViewModel,
    ttsManager: SteosTtsManager,
) {
    val currentNodeId by remember { derivedStateOf { viewModel.currentNodeId } }
    val resources by remember { derivedStateOf { viewModel.resources } }
    var showEndDialog by remember { mutableStateOf(false) }
    var showLockedDialog by remember { mutableStateOf(false) }
    val voiceOn = viewModel.voiceOn

    if (currentNodeId == null && !showEndDialog) {
        showEndDialog = true
    }

    val node = currentNodeId?.let { StoryData.getNode(it) }

    LaunchedEffect(viewModel.currentNodeId, viewModel.voiceOn) {
        if (!viewModel.voiceOn) return@LaunchedEffect

        val dlg = viewModel.currentNodeId
            ?.let { StoryData.getNode(it) }
            ?: return@LaunchedEffect

        val txt = when (dlg) {
            is DialogueNode.Line -> dlg.text
            is DialogueNode.Choice -> dlg.text
        }
        ttsManager.speak(txt)

        when (dlg) {
            is DialogueNode.Line -> {
                delay(1000)
                viewModel.onNextLine()
            }

            is DialogueNode.Choice -> {
//                delay(4000)
//                dlg.options.lastOrNull()?.let { option ->
//                    viewModel.onOptionSelected(option)
//                }
            }
        }
    }

    if (showLockedDialog) {
        ConfirmationDialog(
            title = "Доступная сюжетная арка пройдена",
            message = "Развитие сюжета заблокировано. Хотите пройти заново?",
            confirmButtonText = "Да",
            dismissButtonText = "Нет",
            onConfirm = {
                viewModel.restartStory()
                showLockedDialog = false
            },
            onDismiss = {
                showLockedDialog = false
                onNavigateToCharacter()
            }
        )
        return
    }

    if (showEndDialog) {
        ConfirmationDialog(
            title = "Этот сюжет полностью пройден",
            message = "Вы хотите пройти сюжет снова?",
            confirmButtonText = "Да",
            dismissButtonText = "Нет",
            onConfirm = {
                viewModel.restartStory()
                showEndDialog = false
            },
            onDismiss = {
                showEndDialog = false
                onNavigateToCharacter()
            }
        )
        return
    }

    currentNodeId?.let { nodeId ->
        val dlg = StoryData.getNode(nodeId) ?: return@let

        Box(modifier = Modifier.fillMaxSize()) {
            // Фон
            val bgName = (dlg as? DialogueNode.Line)?.background
                ?: (dlg as? DialogueNode.Choice)?.background
            if (bgName != null) SceneBackground(bgName)
            else MatrixBackground()

            IconButton(
                onClick = { viewModel.toggleVoice() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 50.dp, top = 8.dp)
            ) {
                Icon(
                    imageVector = if (viewModel.voiceOn) Icons.Filled.Mic else Icons.Filled.MicOff,
                    contentDescription = if (viewModel.voiceOn) "Отключить озвучку" else "Включить озвучку",
                    tint = text2
                )
            }

            IconButton(
                onClick = onNavigateToKeyInput,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Group,
                    contentDescription = "Показать ресурсы",
                    tint = text2
                )
            }

            val rawSpeakers = when (dlg) {
                is DialogueNode.Line -> dlg.visibleCharacters
                is DialogueNode.Choice -> dlg.visibleCharacters
            }
            val speakersToShow: List<Speaker> = rawSpeakers
                .filter { it != Speaker.NARRATOR }
                .ifEmpty { listOf((dlg as? DialogueNode.Line)?.speaker ?: (dlg as DialogueNode.Choice).speaker) }

            when (speakersToShow.size) {
                1 -> CharacterOverlay(
                    speaker = speakersToShow[0],
                    modifier = Modifier.align(Alignment.Center)
                )

                2 -> {
                    CharacterOverlay(
                        speaker = speakersToShow[0],
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = (-90).dp),
                        flipHorizontally = true
                    )
                    CharacterOverlay(
                        speaker = speakersToShow[1],
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 90.dp)
                    )
                }

                else -> {}
            }

            DialogPanel(
                modifier = Modifier.align(Alignment.BottomCenter),
                textSize = viewModel.dialogueTextSize,
                fontIdx = viewModel.dialogueFontIndex,
                node = dlg,
                onNextClicked = {
                    val next = (dlg as? DialogueNode.Line)?.nextId
                    if (viewModel.currentCharacter == "bernard" && !viewModel.isBernardChapterUnlocked(
                            next
                        )
                    ) {
                        showLockedDialog = true
                    } else viewModel.onNextLine()
                },
                onOptionSelected = { option ->
                    if (viewModel.currentCharacter == "bernard" && !viewModel.isBernardChapterUnlocked(
                            option.nextId
                        )
                    ) {
                        showLockedDialog = true
                    } else viewModel.onOptionSelected(option)
                }
            )
        }
    }
}
