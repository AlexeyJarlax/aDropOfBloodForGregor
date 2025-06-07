package com.pavlovalexey.adropofbloodforgregor.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms.CharacterCard
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.CustomMultiCard

@Composable
fun CharacterScreen(onNavigateToStory: (String) -> Unit) {
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

            CustomMultiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onNavigateToStory("lilian") }
            ) {
                CharacterCard(
                    name = "Лилиан",
                    imageRes = R.drawable.scene_4,
                    progress = "0%",
                    isColored = true
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
                    progress = "0%",
                    isColored = true
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
                    progress = "0%",
                    isColored = false
                )
            }
        }
    }
}
