package com.jddev.simpletouch.data.repository

import com.jddev.simpletouch.domain.model.ThemeMode
import com.jddev.simpletouch.domain.repository.SettingsRepository

class SettingsRepositoryImpl() : SettingsRepository {
    override suspend fun getThemeMode(): ThemeMode {
        TODO("Not yet implemented")
    }

    override suspend fun getDummyRecord() {
        TODO("Not yet implemented")
    }
}
