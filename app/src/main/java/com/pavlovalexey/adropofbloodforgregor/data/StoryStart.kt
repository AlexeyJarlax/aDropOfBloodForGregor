package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */


object StoryStart {
    const val GREGOR_START = "gregor_intro_1"
    const val LILIAN_START = "lilian_intro_1"
    const val BERNARD_START = "bernard_intro_1"
    const val ASTRA_START  = "astra_intro_1"

    fun prefsKeyFor(character: String): String = "progress_${character.lowercase()}"

    fun prefsProgressKeyFor(character: String): String =
        "progress_${character.lowercase()}"

    fun prefsNodeKeyFor(character: String): String =
        "currentNode_${character.lowercase()}"

    fun nodeIdFor(character: String): NodeId = when (character.lowercase()) {
        "gregor"  -> GREGOR_START
        "lilian"  -> LILIAN_START
        "bernard" -> BERNARD_START
        "astra"   -> ASTRA_START
        else      -> LILIAN_START
    }
}