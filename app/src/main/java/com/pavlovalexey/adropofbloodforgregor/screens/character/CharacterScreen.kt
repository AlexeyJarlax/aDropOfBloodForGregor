package com.pavlovalexey.adropofbloodforgregor.screens.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.CustomMultiCard
import com.pavlovalexey.adropofbloodforgregor.vm.GameViewModel
import kotlin.math.roundToInt

@Composable
fun CharacterScreen(
    onNavigateToStory: (String) -> Unit,
    viewModel: GameViewModel,
) {
    val resources = viewModel.resources.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        MatrixBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            fun formatProgress(char: String): String {
                val p = viewModel.getCharacterProgress(char)
                return "${p.roundToInt()}%"
            }

            CustomMultiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onNavigateToStory("lilian") }
            ) {
                CharacterCard(
                    name = "Лилиан",
                    imageRes = R.drawable.model1_2,
                    progress = formatProgress("lilian"),
                    isColored = viewModel.isCharacterColored("lilian")
                )
            }

            CustomMultiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onNavigateToStory("bernard") }
            ) {
                CharacterCard(
                    name = "Бернард",
                    imageRes = R.drawable.model_b,
                    progress = formatProgress("bernard"),
                    isColored = viewModel.isCharacterColored("bernard")
                )
            }

            CustomMultiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onNavigateToStory("gregor") }
            ) {
                CharacterCard(
                    name = "Грегор",
                    imageRes = R.drawable.scene_1,
                    progress = formatProgress("gregor"),
                    isColored = viewModel.isCharacterColored("gregor")
                )
            }

            CustomMultiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onNavigateToStory("astra") }
            ) {
                CharacterCard(
                    name = "Астра",
                    imageRes = R.drawable.scene_3,
                    progress = formatProgress("astra"),
                    isColored = viewModel.isCharacterColored("astra")
                )
            }
        }
    }
}