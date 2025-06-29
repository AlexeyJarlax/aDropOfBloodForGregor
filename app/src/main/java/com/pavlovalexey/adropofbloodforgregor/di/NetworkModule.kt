package com.pavlovalexey.adropofbloodforgregor.di

/** Павлов Алексей https://github.com/AlexeyJarlax */

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pavlovalexey.adropofbloodforgregor.utils.BASE_URL
import com.pavlovalexey.adropofbloodforgregor.utils.SteosVoiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //    private const val BASE_URL = "https://steos.io/voice/api/"
//    private const val BASE_URL = "https://public.api.voice.steos.io/api/v1/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val log = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(log)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideSteosVoiceApi(retrofit: Retrofit): SteosVoiceApi =
        retrofit.create(SteosVoiceApi::class.java)
}