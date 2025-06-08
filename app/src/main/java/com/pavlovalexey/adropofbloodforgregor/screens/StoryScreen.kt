package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.data.ChoiceOption
import com.pavlovalexey.adropofbloodforgregor.data.DialogueNode
import com.pavlovalexey.adropofbloodforgregor.data.Speaker
import com.pavlovalexey.adropofbloodforgregor.screens.character.CharacterOverlay
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ChoiceButton
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.DialogueText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.SpeakerNameText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground
import androidx.hilt.navigation.compose.hiltViewModel
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ConfirmationDialog
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.data.StoryData
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel

@Composable
fun StoryScreen(
    onNavigateToCharacter: () -> Unit,
    onNavigateToKeyInput: () -> Unit,
    viewModel: GameViewModel
) {
    val currentNodeId by remember { derivedStateOf { viewModel.currentNodeId } }
    val resources by remember { derivedStateOf { viewModel.resources } }

    var showEndDialog by remember { mutableStateOf(false) }

    if (currentNodeId == null && !showEndDialog) {
        showEndDialog = true
    }

    if (showEndDialog) {
        ConfirmationDialog(
            title = "Доступный виток сюжета завершен",
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
        val node = StoryData.getNode(nodeId) ?: return@let

        Box(modifier = Modifier.fillMaxSize()) {
            MatrixBackground()

            IconButton(
                onClick = onNavigateToKeyInput,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Показать ресурсы",
                    tint = Color.White
                )
            }

            val speakersToShow: List<Speaker> = when (node) {
                is DialogueNode.Line -> {
                    if (node.visibleCharacters.any { it != Speaker.NARRATOR }) {
                        node.visibleCharacters.filter { it != Speaker.NARRATOR }
                    } else {
                        listOf(node.speaker).filter { it != Speaker.NARRATOR }
                    }
                }

                is DialogueNode.Choice -> {
                    if (node.visibleCharacters.any { it != Speaker.NARRATOR }) {
                        node.visibleCharacters.filter { it != Speaker.NARRATOR }
                    } else {
                        listOf(node.speaker).filter { it != Speaker.NARRATOR }
                    }
                }
            }
            when (speakersToShow.size) {
                1 -> {
                    CharacterOverlay(
                        speaker = speakersToShow[0],
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                2 -> {
                    CharacterOverlay(
                        speaker = speakersToShow[0],
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = (-90).dp)
                    )
                    CharacterOverlay(
                        speaker = speakersToShow[1],
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 90.dp)
                    )
                }

                else -> {
                }
            }
            DialogPanel(
                modifier = Modifier.align(Alignment.BottomCenter),
                node = node,
                onNextClicked = { viewModel.onNextLine() },
                onOptionSelected = { option -> viewModel.onOptionSelected(option) }
            )

        }
    }
}

@Composable
fun DialogPanel(
    modifier: Modifier = Modifier,
    node: DialogueNode,
    onNextClicked: () -> Unit,
    onOptionSelected: (ChoiceOption) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(0xAA000000))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            when (node) {
                is DialogueNode.Line -> {
                    SpeakerNameText(text = node.speaker.name)
                    Spacer(modifier = Modifier.height(8.dp))
                    // оборачиваем текст и кнопку в Box
                    Box(modifier = Modifier.fillMaxWidth()) {
                        DialogueText(
                            text = node.text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 48.dp)
                        )
                        CustomButtonOne(
                            onClick = onNextClicked,
                            icon = Icons.Filled.ArrowForward,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                        )
                    }
                }

                is DialogueNode.Choice -> {
                    SpeakerNameText(text = node.speaker.name)
                    Spacer(modifier = Modifier.height(8.dp))
                    DialogueText(text = node.text)
                    Spacer(modifier = Modifier.height(12.dp))
                    Column {
                        node.options.forEach { option ->
                            ChoiceButton(
                                text = option.optionText,
                                onClick = { onOptionSelected(option) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}