package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class CharacterStats(
    var health: Float = 100f,
    var hunger: Float = 0f,
    var progress: Float = 0f,
    val chaptersDone: MutableSet<String> = mutableSetOf(),
)