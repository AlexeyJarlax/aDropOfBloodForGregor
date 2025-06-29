package com.pavlovalexey.adropofbloodforgregor.data

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.InputStream
import android.util.Log
import java.io.IOException
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonSubTypes

object StoryData {
    private const val TAG = "StoryData"

    private val yaml = YAMLMapper()
        .registerKotlinModule()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    private val effectRegistry: Map<String, (List<Float>) -> Effect> = mapOf(
        "defaultHungerIncrease" to { _ -> Effects.defaultHungerIncrease },
        "eatFruit" to { _ -> Effects.eatFruit },
        "foundMoonWine1601" to { _ -> Effects.foundMoonWine1601 },
        "foundMoonWine1607" to { _ -> Effects.foundMoonWine1607 },
        "foundMoonWine1608" to { _ -> Effects.foundMoonWine1608 },
        "foundMoonWine1611" to { _ -> Effects.foundMoonWine1611 },
        "foundMoonWine1614" to { _ -> Effects.foundMoonWine1614 },
        "lilianHeal" to { params -> Effects.lilianHeal(params.getOrNull(0) ?: 15f) },
        "markChapterComplete" to { params ->
            val char = params.getOrNull(0)?.toInt() ?: 0
            val chapter = params.getOrNull(1)?.toInt() ?: 1
            Effects.markChapterComplete(charIndex = char, chapter = chapter)
        }
    )


    private var nodes: Map<NodeId, DialogueNode> = emptyMap()

    fun init(context: Context, character: String) {
        val totalChapters = when (character) {
            "bernard" -> 4
            "lilian", "astra" -> 8
            else -> 1
        }

        val allNodes = mutableMapOf<NodeId, DialogueNode>()
        for (i in 1..totalChapters) {
            val assetName = "story_data_${character}_chap${i}.yaml"
            try {
                context.assets.open(assetName).use { input ->
                    val dto = yaml.readValue(input, StoryFileDto::class.java)
                    dto.nodes.forEach { nodeDto ->
                        val key = nodeDto.id
                        val node = nodeDto.toDialogueNode()
                        allNodes[key] = node
                    }
                }
                Log.i(TAG, "Loaded $assetName")
            } catch (_: IOException) {
                Log.w(TAG, "Не найден $assetName, пропускаем")
            }
        }
        nodes = allNodes
    }

    fun getNode(nodeId: NodeId): DialogueNode? = nodes[nodeId]

    private fun loadFromStream(input: InputStream) {
        val dto = yaml.readValue(input, StoryFileDto::class.java)
        nodes = dto.nodes.associateBy { it.id }
            .mapValues { (_, nodeDto) -> nodeDto.toDialogueNode() }
    }

    private data class StoryFileDto(val nodes: List<NodeDto>)

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
    )
    @JsonSubTypes(
        JsonSubTypes.Type(value = NodeDto.Line::class, name = "Line"),
        JsonSubTypes.Type(value = NodeDto.Choice::class, name = "Choice")
    )

    private sealed class NodeDto {
        abstract val id: String
        abstract val speaker: Speaker
        abstract val text: String
        abstract val effects: List<String>
        abstract fun toDialogueNode(): DialogueNode

        data class Line(
            override val id: String,
            override val speaker: Speaker,
            override val text: String,
            val visibleCharacters: List<Speaker> = emptyList(),
            override val effects: List<String> = emptyList(),
            val nextId: String?,
            val background: String? = null,
        ) : NodeDto() {
            override fun toDialogueNode(): DialogueNode {
                val validSpeakers = Speaker.values().toSet()
                val validVisible = visibleCharacters.filter {
                    if (it !in validSpeakers) {
                        Log.w(TAG, "Невалидный персонаж в visibleCharacters: $it")
                        false
                    } else true
                }

                val safeSpeaker = if (speaker in validSpeakers) speaker else {
                    Log.w(TAG, "Невалидный speaker \"$speaker\" в node $id, используется Speaker.NARRATOR")
                    Speaker.NARRATOR
                }

                return DialogueNode.Line(
                    id = id,
                    speaker = safeSpeaker,
                    text = text,
                    visibleCharacters = validVisible,
                    effects = effects.mapNotNull { parseEffectWithLogging(it) },
                    nextId = nextId,
                    background = background
                )
            }
        }

        data class Choice(
            override val id: String,
            override val speaker: Speaker,
            override val text: String,
            val visibleCharacters: List<Speaker> = emptyList(),
            val options: List<ChoiceOptionDto>,
            override val effects: List<String> = emptyList(),
            val background: String? = null,
        ) : NodeDto() {
            override fun toDialogueNode(): DialogueNode {
                val validSpeakers = Speaker.values().toSet()
                val validVisible = visibleCharacters.filter {
                    if (it !in validSpeakers) {
                        Log.w(TAG, "Невалидный персонаж в visibleCharacters: $it")
                        false
                    } else true
                }

                val safeSpeaker = if (speaker in validSpeakers) speaker else {
                    Log.w(TAG, "Невалидный speaker \"$speaker\" в node $id, используется Speaker.NARRATOR")
                    Speaker.NARRATOR
                }

                return DialogueNode.Choice(
                    id = id,
                    speaker = safeSpeaker,
                    text = text,
                    visibleCharacters = validVisible,
                    options = options.map { it.toChoiceOption() },
                    background = background
                )
            }
        }
    }

    private data class ChoiceOptionDto(
        val optionText: String,
        val effects: List<String> = emptyList(),
        val nextId: String?,
    ) {
        fun toChoiceOption(): ChoiceOption = ChoiceOption(
            optionText = optionText,
            effects = effects.mapNotNull { parseEffect(it) },
            nextId = nextId
        )
    }

    private fun parseEffect(raw: String): Effect? {
        val name = raw.substringBefore('(')
        val params = raw.substringAfter('(', "").substringBefore(')').takeIf { it.isNotBlank() }
            ?.split(',')
            ?.mapNotNull { it.toFloatOrNull() }
            ?: emptyList()
        return effectRegistry[name]?.invoke(params)
    }

    private fun parseEffectWithLogging(raw: String): Effect? {
        val name = raw.substringBefore('(')
        val params = raw.substringAfter('(', "").substringBefore(')').takeIf { it.isNotBlank() }
            ?.split(',')
            ?.mapNotNull { it.toFloatOrNull() }
            ?: emptyList()
        val effect = effectRegistry[name]
        return if (effect != null) {
            effect(params)
        } else {
            Log.w(TAG, "Невалидный эффект \"$name\"")
            null
        }
    }
}