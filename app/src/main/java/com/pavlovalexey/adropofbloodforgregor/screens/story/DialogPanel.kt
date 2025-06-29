package com.pavlovalexey.adropofbloodforgregor.screens.story

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.data.ChoiceOption
import com.pavlovalexey.adropofbloodforgregor.data.DialogueNode
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.ChoiceButton
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CustomButtonOne
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.DialogueBoldText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.DialogueText
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.SpeakerNameText

@Composable
fun DialogPanel(
    modifier: Modifier = Modifier,
    node: DialogueNode,
    onNextClicked: () -> Unit,
    onOptionSelected: (ChoiceOption) -> Unit,
    textSize: Int,
    fontIdx: Int,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.matchParentSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xAA000000)
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xAA000000))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            when (node) {
                is DialogueNode.Line -> {
                    SpeakerNameText(text = node.speaker.name)
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.fillMaxWidth()) {
                        DialogueText(
                            text = node.text,
                            textSize = textSize,
                            fontIdx = fontIdx,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 48.dp)
                        )
                        CustomButtonOne(
                            onClick = onNextClicked,
                            icon = Icons.Filled.ArrowForward,
                            modifier = Modifier.align(Alignment.BottomEnd)
                        )
                    }
                }
                is DialogueNode.Choice -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpeakerNameText(text = node.speaker.name)
                        Spacer(Modifier.width(8.dp))
                        DialogueBoldText(
                            text = node.text,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Column {
                        node.options.forEach { option ->
                            ChoiceButton(
                                text = option.optionText,
                                textSize = textSize,
                                fontIdx = fontIdx,
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