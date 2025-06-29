package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class Resources(
    val gregor: CharacterStats = CharacterStats(),
    val lilian: CharacterStats = CharacterStats(),
    val bernard: CharacterStats = CharacterStats(),
    val astra: CharacterStats = CharacterStats(),
    var wine: Float = 0f,
    var lilianHasMoonWine1601: Boolean = false,
    var lilianHasMoonWine1607: Boolean = false,
    var lilianHasMoonWine1608: Boolean = false,
    var lilianHasMoonWine1611: Boolean = false,
    var lilianHasMoonWine1614: Boolean = false
) {
    val progress: MutableMap<String, Float> = mutableMapOf(
        "gregor" to gregor.progress,
        "lilian" to lilian.progress,
        "bernard" to bernard.progress,
        "astra" to astra.progress
    )

    fun getStats(char: String): CharacterStats = when (char) {
        "gregor" -> gregor
        "lilian" -> lilian
        "bernard" -> bernard
        "astra"  -> astra
        else -> error("Unknown character: $char")
    }
}