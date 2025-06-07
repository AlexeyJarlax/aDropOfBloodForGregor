package com.pavlovalexey.adropofbloodforgregor.vm

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val prefs: SharedPreferences
) : ViewModel()