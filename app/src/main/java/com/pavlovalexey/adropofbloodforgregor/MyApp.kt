package com.pavlovalexey.adropofbloodforgregor

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.app.Application
//import androidx.lifecycle.ProcessLifecycleOwner
import com.pavlovalexey.adropofbloodforgregor.utils.ToastExt
import com.pavlovalexey.adropofbloodforgregor.data.StoryData
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
//    lateinit var appLifecycleObserver: AppLifecycleObserver

    override fun onCreate() {
        super.onCreate()
//        StoryData.init(this)
//        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)

        ToastExt.init(this)
    }
}