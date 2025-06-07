package com.pavlovalexey.adropofbloodforgregor.di

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Context
import android.content.SharedPreferences
import com.pavlovalexey.adropofbloodforgregor.utils.SHARE_PREF
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)
    }
}