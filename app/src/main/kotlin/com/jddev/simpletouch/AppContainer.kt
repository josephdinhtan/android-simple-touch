package com.jddev.simpletouch

import android.content.Context
import com.jddev.simpletouch.ui.debug.DevUtility
import com.jddev.simpletouch.ui.settings.AppSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppContainer @Inject constructor(
    @ApplicationContext val applicationContext: Context,
    val devUtility: DevUtility,
    val appSettings: AppSettings,
)