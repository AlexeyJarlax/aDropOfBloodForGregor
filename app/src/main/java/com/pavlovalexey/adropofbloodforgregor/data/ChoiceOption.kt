package com.pavlovalexey.adropofbloodforgregor.data

/** Павлов Алексей https://github.com/AlexeyJarlax */

data class ChoiceOption(
    val optionText: String,
    val effects: List<Effect> = emptyList(),
    val nextId: NodeId?
)