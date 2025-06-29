package com.pavlovalexey.adropofbloodforgregor.utils

/** Павлов Алексей https://github.com/AlexeyJarlax */

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.GET

data class VoiceItem(
    val id: Int,
    val name: String
)

interface SteosVoiceApi {
    @POST("synthesize-controller/synthesis-by-text")
    suspend fun synthesize(
        @Query("authToken") authToken: String,
        @Body request: SynthesisRequest
    ): Response<SynthesisResponse>

    @GET("steos-voice-controller/available-voices")
    suspend fun getAvailableVoices(
        @Query("authToken") authToken: String
    ): Response<List<VoiceItem>>
}

data class SynthesisRequest(
    val voiceId: Int = VOICE_ID_DEF,
    val text: String,
    val pitchShift: Double = VOICE_PITCH_DEF,
    val speedMultiplier: Double = VOICE_SPEED_DEF
)

data class SynthesisResponse(
    val fileContents: String
)