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

    val eatFruit: Effect = { resources ->
        resources.lilian.hunger =
            (resources.lilian.hunger - 15f).coerceAtLeast(0f)
    }

    val foundMoonWine1601: Effect = { resources ->
        if (!resources.lilianHasMoonWine1601) {
            resources.lilianHasMoonWine1601 = true
            Log.d("Effects", "Лилиан получила Лунное вино 1601")
        }
    }

    val foundMoonWine1607: Effect = { resources ->
        if (!resources.lilianHasMoonWine1607) {
            resources.lilianHasMoonWine1607 = true
            Log.d("Effects", "Лилиан получила Лунное вино 1607")
        }
    }

    val foundMoonWine1608: Effect = { resources ->
        if (!resources.lilianHasMoonWine1608) {
            resources.lilianHasMoonWine1608 = true
            Log.d("Effects", "Лилиан получила Лунное вино 1608")
        }
    }

    val foundMoonWine1611: Effect = { resources ->
        if (!resources.lilianHasMoonWine1611) {
            resources.lilianHasMoonWine1611 = true
            Log.d("Effects", "Лилиан получила Лунное вино 1611")
        }
    }

    val foundMoonWine1614: Effect = { resources ->
        if (!resources.lilianHasMoonWine1614) {
            resources.lilianHasMoonWine1614 = true
            Log.d("Effects", "Лилиан получила Лунное вино 1614")
        }
    }

    fun lilianHeal(amount: Float = 15f): Effect = { resources ->
        resources.lilian.health = (resources.lilian.health + amount).coerceIn(0f, 100f)
    }

    fun markChapterComplete(charIndex: Int, chapter: Int): Effect = { resources ->
        val charKey = listOf("gregor","lilian","bernard","astra")[charIndex]
        val stats   = resources.getStats(charKey)
        val id = "${charKey}_chap${chapter}"
        stats.chaptersDone.add(id)
    }
}