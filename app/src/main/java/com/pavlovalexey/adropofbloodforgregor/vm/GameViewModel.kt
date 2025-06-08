package com.pavlovalexey.adropofbloodforgregor.vm

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.pavlovalexey.adropofbloodforgregor.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val prefs: SharedPreferences,
) : AndroidViewModel(application) {

    var currentCharacter by mutableStateOf<String?>(null)
        private set

    var currentNodeId by mutableStateOf<NodeId?>(null)
        private set

    private val _resources = MutableStateFlow(Resources())
    val resources: StateFlow<Resources> = _resources

    init {
        _resources.value = loadAllResourcesFromPrefs()
        savedStateHandle.get<String>("character")?.let { selectCharacter(it) }
    }

    private fun loadAllResourcesFromPrefs(): Resources {
        val res = Resources()

        res.wine = getFloatCompat("wine", 0f)

        listOf("lilian", "bernard", "gregor", "astra").forEach { char ->
            val stats = res.getStats(char)
            stats.health = getFloatCompat("${char}_health", 100f)
            stats.hunger = getFloatCompat("${char}_hunger", 0f)
            val progKey = StoryStart.prefsProgressKeyFor(char)
            val prog = getFloatCompat(progKey, 0f)
            stats.progress = prog
            res.progress[char] = prog
            val doneKey = "chaptersDone_$char"
            val savedSet = prefs.getStringSet(doneKey, emptySet()) ?: emptySet()
            stats.chaptersDone.clear()
            stats.chaptersDone.addAll(savedSet)
        }

        return res
    }

    private fun getFloatCompat(key: String, default: Float): Float {
        return try {
            prefs.getFloat(key, default)
        } catch (e: ClassCastException) {
            val str = prefs.getString(key, null)
            val value = str?.toFloatOrNull() ?: default
            prefs.edit().remove(key).putFloat(key, value).apply()
            value
        }
    }

    fun selectCharacter(character: String) {
        val charKey = character.lowercase()
        currentCharacter = charKey
        StoryData.init(getApplication(), charKey)
        val nodeKey = StoryStart.prefsNodeKeyFor(charKey)
        val savedNode = prefs.getString(nodeKey, null)
        currentNodeId = savedNode ?: StoryStart.nodeIdFor(charKey).also {
            prefs.edit().putString(nodeKey, it).apply()
        }
    }

    private fun loadResources(character: String): Resources {
        val res = Resources()
        listOf("lilian", "bernard", "gregor", "astra").forEach { char ->
            // health
            val healthKey = "${char}_health"
            val health = getFloatCompat(healthKey, 100f)
            res.getStats(char).health = health

            // hunger
            val hungerKey = "${char}_hunger"
            val hunger = getFloatCompat(hungerKey, 0f)
            res.getStats(char).hunger = hunger

            // progress
            val progKey = StoryStart.prefsProgressKeyFor(char)
            val prog = getFloatCompat(progKey, 0f)
            res.progress[char] = prog
            res.getStats(char).progress = prog
        }
        return res
    }

    fun onNextLine() {
        val char = currentCharacter ?: return
        val nodeId = currentNodeId ?: return
        val node = StoryData.getNode(nodeId) ?: return
        if (node is DialogueNode.Line) {
            applyDefaultEffects(char)
            applyEffects(node.effects, char)
            advanceNode(node.nextId)
        }
    }

    fun onOptionSelected(option: ChoiceOption) {
        val char = currentCharacter ?: return
        applyDefaultEffects(char)
        applyEffects(option.effects, char)
        advanceNode(option.nextId)
    }

    private fun advanceNode(nextId: NodeId?) {
        currentNodeId = nextId
        saveNode(nextId)
    }

    private fun saveNode(nodeId: NodeId?) {
        currentCharacter?.let { char ->
            val key = StoryStart.prefsNodeKeyFor(char)
            if (nodeId != null) prefs.edit().putString(key, nodeId).apply()
        }
    }

    private fun applyDefaultEffects(character: String) {
        Effects.defaultHungerIncrease(_resources.value)
        persistStats(character)
    }

    private fun applyEffects(effects: List<Effect>, character: String) {
        val res = _resources.value
        effects.forEach { it(res) }
        persistStats(character)
        _resources.value = res
    }

    private fun persistStats(character: String) {
        val stats = _resources.value.getStats(character)
        with(prefs.edit()) {
            putFloat("${character}_health", stats.health)
            putFloat("${character}_hunger", stats.hunger)
            putFloat(StoryStart.prefsProgressKeyFor(character), stats.progress)
            putStringSet("chaptersDone_$character", stats.chaptersDone)
            apply()
        }
    }

    fun restartStory() {
        currentCharacter?.let { char ->
            val startNode = StoryStart.nodeIdFor(char)
            currentNodeId = startNode
            val nodeKey = StoryStart.prefsNodeKeyFor(char)
            prefs.edit().putString(nodeKey, startNode).apply()
            StoryData.init(getApplication(), char)
        }
    }

    fun exitStory() {
        currentNodeId = null
    }

    private fun Resources.getStats(char: String): CharacterStats =
        when (char) {
            "lilian" -> lilian
            "bernard" -> bernard
            "gregor" -> gregor
            "astra" -> astra
            else -> error("Unknown character: $char")
        }

    fun getCharacterProgress(character: String): Float = resources.value.progress[character] ?: 0f

    fun isCharacterColored(character: String): Boolean {
        return if (character == "gregor") false
        else if (character == "astra") false
        else true
    }

    fun resetAllStates() {
        prefs.edit().clear().apply()

        listOf("gregor", "lilian", "astra", "bernard").forEach { char ->
            val progKey = StoryStart.prefsProgressKeyFor(char)
            val nodeKey = StoryStart.prefsNodeKeyFor(char)
            prefs.edit()
                .putFloat(progKey, 0f)
                .putString(nodeKey, StoryStart.nodeIdFor(char))
                .putStringSet("chaptersDone_$char", emptySet())
                .apply()
        }

        currentCharacter = null
        currentNodeId = null
        _resources.value = Resources()
    }
}