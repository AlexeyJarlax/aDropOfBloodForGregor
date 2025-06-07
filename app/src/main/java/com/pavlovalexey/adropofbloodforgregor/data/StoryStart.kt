package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */


object StoryStart {
    const val GREGOR_START = "gregor_intro_1"
    const val LILIAN_START = "lilian_intro_1"
    const val ASTRA_START  = "astra_intro_1"


    fun nodeIdFor(character: String): NodeId = when (character.lowercase()) {
        "gregor" -> GREGOR_START
        "lilian" -> LILIAN_START
        "astra"  -> ASTRA_START
        else     -> LILIAN_START
    }

    fun prefsKeyFor(character: String): String = "progress_${character.lowercase()}"
}