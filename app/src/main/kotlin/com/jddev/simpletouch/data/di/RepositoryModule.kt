package com.jddev.simpletouch.data.di

import android.content.Context
import com.jddev.simpletouch.data.repository.NotificationRepositoryImpl
import com.jddev.simpletouch.data.repository.SettingsRepositoryImpl
import com.jddev.simpletouch.domain.repository.NotificationRepository
import com.jddev.simpletouch.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun bindSettingsRepository(
    ): SettingsRepository = SettingsRepositoryImpl()

    @Singleton
    @Provides
    fun bindNotificationRepository(
        @ApplicationContext context: Context,
    ): NotificationRepository = NotificationRepositoryImpl(context)
}