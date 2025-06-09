package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.pavlovalexey.adropofbloodforgregor.ui.theme.customs.MatrixBackground

/** Павлов Алексей https://github.com/AlexeyJarlax */

@Composable
fun SceneBackground(imageName: String) {
    val context = LocalContext.current
    val resourceName = imageName.substringBeforeLast('.')
    val resId = remember(imageName) {
        context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
    }
    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        MatrixBackground()
    }
}