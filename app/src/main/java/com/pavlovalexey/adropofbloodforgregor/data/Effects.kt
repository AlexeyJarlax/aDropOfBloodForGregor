package com.pavlovalexey.adropofbloodforgregor.data

import android.util.Log

/** Павлов Алексей https://github.com/AlexeyJarlax */

typealias Effect = (Resources) -> Unit

object Effects {
    val defaultHungerIncrease: Effect = { resources ->
        resources.gregor.hunger = (resources.gregor.hunger + 1f).coerceIn(0f, 100f)
        resources.lilian.hunger = (resources.lilian.hunger + 1f).coerceIn(0f, 100f)
        resources.astra.hunger  = (resources.astra.hunger  + 1f).coerceIn(0f, 100f)
    }

    fun gregorDrinksWine(amountWine: Float = 10f, hungerReduce: Float = 10f): Effect = { resources ->
        resources.wine = (resources.wine - amountWine).coerceAtLeast(0f)
        resources.gregor.hunger = (resources.gregor.hunger - hungerReduce).coerceAtLeast(0f)
    }

    fun lilianHeal(amount: Float = 15f): Effect = { resources ->
        resources.lilian.health = (resources.lilian.health + amount).coerceIn(0f, 100f)
    }

    // Идемпотентные эффекты завершения глав
    fun markBernardChapterOneComplete(amount: Float = 5f): Effect = { res ->
        val stats = res.bernard
        val effectId = "markBernardChapterOneComplete"
        if (stats.chaptersDone.add(effectId)) {
            stats.progress = (stats.progress + amount).coerceAtMost(100f)
            res.progress["bernard"] = stats.progress
            Log.i("Effects", "Bernard chapter1 first run: progress=${stats.progress}")
        }
    }

    fun markLilianChapterOneComplete(amount: Float = 5f): Effect = { res ->
        val stats = res.lilian
        val effectId = "markLilianChapterOneComplete"
        if (stats.chaptersDone.add(effectId)) {
            stats.progress = (stats.progress + amount).coerceAtMost(100f)
            res.progress["lilian"] = stats.progress
            Log.i("Effects", "Lilian chapter1 first run: progress=${stats.progress}")
        }
    }

    fun markGregorChapterOneComplete(amount: Float = 5f): Effect = { res ->
        val stats = res.gregor
        val effectId = "markGregorChapterOneComplete"
        if (stats.chaptersDone.add(effectId)) {
            stats.progress = (stats.progress + amount).coerceAtMost(100f)
            res.progress["gregor"] = stats.progress
            Log.i("Effects", "Gregor chapter1 first run: progress=${stats.progress}")
        }
    }

    fun markAstraChapterOneComplete(amount: Float = 5f): Effect = { res ->
        val stats = res.astra
        val effectId = "markAstraChapterOneComplete"
        if (stats.chaptersDone.add(effectId)) {
            stats.progress = (stats.progress + amount).coerceAtMost(100f)
            res.progress["astra"] = stats.progress
            Log.i("Effects", "Astra chapter1 first run: progress=${stats.progress}")
        }
    }
}