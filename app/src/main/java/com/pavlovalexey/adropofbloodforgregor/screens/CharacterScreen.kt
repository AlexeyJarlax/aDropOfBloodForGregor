package com.pavlovalexey.adropofbloodforgregor.screens

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.pavlovalexey.adropofbloodforgregor.R
import com.pavlovalexey.adropofbloodforgregor.ui.theme.LabelLarge_14_Medium
import com.pavlovalexey.adropofbloodforgregor.ui.theme.TitleLarge_22_Regular
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.CustomMultiCard

@Composable
fun CharacterScreen(onNavigateToStory: () -> Unit) {
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
            CustomMultiCard {
                CharacterCard(
                    name = "Грегор",
                    imageRes = R.drawable.scene_1,
                    progress = "0%",
                    isColored = true
                )
            }
            CustomMultiCard {
                CharacterCard(
                    name = "Лилиан",
                    imageRes = R.drawable.scene_4,
                    progress = "0%",
                    isColored = false
                )
            }
            CustomMultiCard {
                CharacterCard(
                    name = "Астария",
                    imageRes = R.drawable.scene_3,
                    progress = "0%",
                    isColored = false
                )
            }
        }
    }
}