package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class Resources(
    val gregor: CharacterStats = CharacterStats(),
    val lilian: CharacterStats = CharacterStats(),
    val bernard: CharacterStats = CharacterStats(),
    val astra: CharacterStats = CharacterStats(),
    var wine: Float = 0f
) {
    val progress: MutableMap<String, Float> = mutableMapOf(
        "gregor" to gregor.progress,
        "lilian" to lilian.progress,
        "bernard" to bernard.progress,
        "astra" to astra.progress
    )
}