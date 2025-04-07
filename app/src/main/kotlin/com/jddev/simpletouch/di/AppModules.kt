package com.jddev.simpletouch.di

import com.jddev.simpletouch.ui.settings.AppSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideAppSettings(): AppSettings {
        return AppSettings.Default
    }
}