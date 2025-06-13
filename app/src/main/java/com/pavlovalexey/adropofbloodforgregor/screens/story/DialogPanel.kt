package com.pavlovalexey.adropofbloodforgregor.screens.story

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.data.ChoiceOption
import com.pavlovalexey.adropofbloodforgregor.data.DialogueNode
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ChoiceButton
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.DialogueBoldText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.DialogueText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.SpeakerNameText

/** Павлов Алексей https://github.com/AlexeyJarlax */

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
                    Spacer(modifier = Modifier.height(6.dp))
                    DialogueBoldText(text = node.text)
                    Spacer(modifier = Modifier.height(6.dp))
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

