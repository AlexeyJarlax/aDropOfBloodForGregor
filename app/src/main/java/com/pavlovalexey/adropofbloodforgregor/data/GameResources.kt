package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class GameResources(
    val gregor: CharacterStats = CharacterStats(hunger = 0f, health = 100f),
    val lilian: CharacterStats = CharacterStats(hunger = 0f, health = 100f),
    val astra: CharacterStats = CharacterStats(hunger = 0f, health = 100f),
    var bernardHealth: Float = 100f,
    var wine: Float = 0f
)