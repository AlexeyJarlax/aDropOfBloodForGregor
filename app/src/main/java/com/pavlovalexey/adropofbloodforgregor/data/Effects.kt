package com.pavlovalexey.adropofbloodforgregor.data

import android.util.Log

/** Павлов Алексей https://github.com/AlexeyJarlax */

typealias Effect = (Resources) -> Unit

object Effects {
    val defaultHungerIncrease: Effect = { resources ->
        resources.gregor.hunger = (resources.gregor.hunger + 1f).coerceIn(0f, 100f)
        resources.lilian.hunger = (resources.lilian.hunger + 1f).coerceIn(0f, 100f)
        resources.astra.hunger = (resources.astra.hunger + 1f).coerceIn(0f, 100f)
    }

    fun gregorDrinksWine(amountWine: Float = 10f, hungerReduce: Float = 10f): Effect =
        { resources ->
            resources.wine = (resources.wine - amountWine).coerceAtLeast(0f)
            resources.gregor.hunger = (resources.gregor.hunger - hungerReduce).coerceAtLeast(0f)
        }

    fun lilianHeal(amount: Float = 15f): Effect = { resources ->
        resources.lilian.health = (resources.lilian.health + amount).coerceIn(0f, 100f)
    }

        fun markChapterComplete(charIndex: Int, chapter: Int): Effect = { res ->
                val charKey = listOf("gregor","lilian","bernard","astra")[charIndex]
                val stats   = res.getStats(charKey)
                val id      = "${charKey}_chap$chapter"
                stats.chaptersDone.add(id)
            }
}