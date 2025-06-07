package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

typealias NodeId = String

sealed class DialogueNode {
    data class Line(
        val id: NodeId,
        val speaker: Speaker,
        val text: String,
        val visibleCharacters: List<Speaker> = emptyList(),
        val effects: List<Effect> = emptyList(),
        val nextId: NodeId?
    ) : DialogueNode()

    data class Choice(
        val id: NodeId,
        val speaker: Speaker,
        val text: String,
        val options: List<ChoiceOption>
    ) : DialogueNode()
}

data class ChoiceOption(
    val optionText: String,
    val effects: List<Effect> = emptyList(),
    val nextId: NodeId?
)

enum class Speaker {
    GREGOR,
    LILIAN,
    ASTRA,
    BERNARD,
    NARRATOR
}