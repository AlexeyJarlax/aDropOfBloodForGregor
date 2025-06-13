package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.annotation.DrawableRes
import com.pavlovalexey.adropofbloodforgregor.R

enum class Speaker(@DrawableRes val imageRes: Int?) {
    GREGOR(R.drawable.model_g),
    LILIAN(R.drawable.model1_1),
    ASTRA(R.drawable.model_a),
    BERNARD(R.drawable.model_b),
    NOBODY(R.drawable.model_0),
    NARRATOR(null)
}