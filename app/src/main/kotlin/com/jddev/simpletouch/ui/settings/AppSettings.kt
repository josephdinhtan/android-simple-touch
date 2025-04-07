package com.jddev.simpletouch.ui.settings

import com.jddev.simpletouch.ui.settings.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow

data class AppSettings (
    val appThemeMode: MutableStateFlow<AppThemeMode>,
) {
    companion object {
        val Default = AppSettings(
            appThemeMode = MutableStateFlow(AppThemeMode.SYSTEM),
        )
    }
}