package com.jddev.simpletouch.domain.repository

import com.jddev.simpletouch.domain.model.ThemeMode

interface SettingsRepository {
    suspend fun getThemeMode(): ThemeMode
    suspend fun getDummyRecord()
}