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
    viewModel: GameViewModel = hiltViewModel(),
) {
    val resourcesState = viewModel.resources.collectAsState()
    val chars = listOf(
        "lilian"  to R.drawable.model1_2,
        "bernard" to R.drawable.model_b,
        "astra"   to R.drawable.model_a
    )

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
            chars.forEach { (charKey, imageRes) ->
                val isColored = charKey == "lilian"

                val displayName = when (charKey) {
                    "lilian"  -> "Лилиан"
                    "bernard" -> "Бернард"
                    "astra"   -> "Валериан"
                    else      -> charKey
                }
                val totalChapters = viewModel.getTotalChapters(charKey)
                val chaptersDone  = viewModel.getChaptersDone(charKey)
                val unlockedCount = viewModel.getUnlockedChaptersCount(charKey)

                CustomMultiCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { onNavigateToStory(charKey) }
                ) {
                    CharacterCard(
                        name          = displayName,
                        charKey       = charKey,
                        imageRes      = imageRes,
                        isColored     = isColored,
                        totalChapters = totalChapters,
                        chaptersDone  = chaptersDone,
                        unlockedCount = unlockedCount
                    )
                }
            }
        }
    }
}