package com.pavlovalexey.adropofbloodforgregor.vm

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pavlovalexey.adropofbloodforgregor.data.*
import com.pavlovalexey.adropofbloodforgregor.data.StoryData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val prefs: SharedPreferences,
) : AndroidViewModel(application) {

    private val character: String =
        savedStateHandle.get<String>("character")?.lowercase() ?: ""
    private val prefsKey = StoryStart.prefsKeyFor(character)

    var currentNodeId by mutableStateOf<NodeId?>(null)
        private set

    var resources by mutableStateOf(GameResources())
        private set

    init {
        StoryData.init(application, character)
        val savedNode: String? = prefs.getString(prefsKey, null)
        if (savedNode != null) {
            currentNodeId = savedNode
        } else {
            val startNode = StoryStart.nodeIdFor(character)
            currentNodeId = startNode
            prefs.edit().putString(prefsKey, startNode).apply()
        }
        resources = resources.copy(wine = 20f)
    }

    private fun saveProgress(nodeId: NodeId?) {
        if (nodeId != null) {
            prefs.edit().putString(prefsKey, nodeId).apply()
        } else {
        }
    }


    fun onNextLine() {
        val nodeId = currentNodeId ?: return
        val node = StoryData.getNode(nodeId) ?: return

        if (node is DialogueNode.Line) {
            Effects.defaultHungerIncrease(resources)
            node.effects.forEach { it(resources) }
            currentNodeId = node.nextId
            saveProgress(node.nextId)
        }
    }

    fun onOptionSelected(option: ChoiceOption) {
        Effects.defaultHungerIncrease(resources)
        option.effects.forEach { it(resources) }
        currentNodeId = option.nextId
        saveProgress(option.nextId)
    }

    fun onExit() {
        currentNodeId = null
    }

    fun restart() {
        val startNode = StoryStart.nodeIdFor(character)
        currentNodeId = startNode
        saveProgress(startNode)
        resources = GameResources().copy(wine = 20f)
    }
}