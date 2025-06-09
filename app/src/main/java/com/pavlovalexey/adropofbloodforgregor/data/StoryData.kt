package com.pavlovalexey.adropofbloodforgregor.data

import android.content.Context
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
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
        "gregorDrinksWine" to { params ->
            Effects.gregorDrinksWine(
                params.getOrNull(0) ?: 10f,
                params.getOrNull(1) ?: 10f
            )
        },
        "lilianHeal" to { params -> Effects.lilianHeal(params.getOrNull(0) ?: 15f) },

        "markBernardChapterOneComplete" to { params ->
            Effects.markBernardChapterOneComplete(params.getOrNull(0) ?: 5f)
        },
        "markLilianChapterOneComplete"  to { params ->
            Effects.markLilianChapterOneComplete(params.getOrNull(0) ?: 10f)
        },
        "markGregorChapterOneComplete"  to { params ->
            Effects.markGregorChapterOneComplete(params.getOrNull(0) ?: 5f)
        },
        "markAstraChapterOneComplete"   to { params ->
            Effects.markAstraChapterOneComplete(params.getOrNull(0) ?: 5f)
        }
    )

    private var nodes: Map<NodeId, DialogueNode> = emptyMap()

    fun init(context: Context, character: String) {
        val assetName = "story_data_${character}.yaml"
        try {
            context.assets.open(assetName).use { loadFromStream(it) }
            Log.i(TAG, "Loaded local asset $assetName")
        } catch (ioe: IOException) {
            Log.w(TAG, "Asset $assetName not found, falling back to story_data.yaml", ioe)
            try {
                context.assets.open("story_data.yaml").use { loadFromStream(it) }
                Log.i(TAG, "Loaded local fallback story_data.yaml")
            } catch (_: IOException) {
                Log.e(TAG, "Neither $assetName nor story_data.yaml found in assets!")
            }
        }
        fetchRemoteYaml(character)
    }

    fun getNode(nodeId: NodeId): DialogueNode? = nodes[nodeId]

    private fun loadFromStream(input: InputStream) {
        val dto = yaml.readValue(input, StoryFileDto::class.java)
        nodes = dto.nodes.associateBy { it.id }
            .mapValues { (_, nodeDto) -> nodeDto.toDialogueNode() }
    }

    private fun fetchRemoteYaml(character: String) {
        val url =
            "https://raw.githubusercontent.com/AlexeyJarlax/aDropOfBloodForGregor/screenwriter/app/src/main/assets/story_data_${character}.yaml"
        val client = OkHttpClient()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resp = client.newCall(Request.Builder().url(url).build()).execute()
                if (resp.isSuccessful) {
                    resp.body?.byteStream()?.use { loadFromStream(it) }
                    Log.i(TAG, "Fetched remote story_data for $character")
                }
            } catch (t: Throwable) {
                Log.w(TAG, "Failed to fetch remote YAML", t)
            }
        }
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
            val background: String? = null
        ) : NodeDto() {
            override fun toDialogueNode(): DialogueNode = DialogueNode.Line(
                id = id,
                speaker = speaker,
                text = text,
                visibleCharacters = visibleCharacters,
                effects = effects.mapNotNull { parseEffect(it) },
                nextId = nextId,
                background = background
            )
        }

        data class Choice(
            override val id: String,
            override val speaker: Speaker,
            override val text: String,
            val visibleCharacters: List<Speaker> = emptyList(),
            val options: List<ChoiceOptionDto>,
            override val effects: List<String> = emptyList(),
            val background: String? = null
        ) : NodeDto() {
            override fun toDialogueNode(): DialogueNode = DialogueNode.Choice(
                id = id,
                speaker = speaker,
                text = text,
                visibleCharacters = visibleCharacters,
                options = options.map { it.toChoiceOption() },
                background = background
            )
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
}