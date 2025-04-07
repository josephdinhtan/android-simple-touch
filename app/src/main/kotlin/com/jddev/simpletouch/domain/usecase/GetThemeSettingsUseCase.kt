package com.jddev.simpletouch.domain.usecase

import com.jddev.simpletouch.domain.model.ThemeMode
import com.jddev.simpletouch.domain.repository.SettingsRepository
import javax.inject.Inject

class GetThemeSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : BaseUseCase<Unit, ThemeMode> {
    override suspend fun execute(params: Unit): ThemeMode {
        return settingsRepository.getThemeMode()
    }
}