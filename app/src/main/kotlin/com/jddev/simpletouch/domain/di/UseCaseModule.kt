package com.jddev.simpletouch.domain.di

import com.jddev.simpletouch.domain.repository.SettingsRepository
import com.jddev.simpletouch.domain.usecase.GetThemeSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetThemeSettingsUseCase(
        settingsRepository: SettingsRepository,
    ): GetThemeSettingsUseCase {
        return GetThemeSettingsUseCase(settingsRepository)
    }
}