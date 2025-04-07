package com.jddev.simpletouch.ui.basic.notification

import androidx.lifecycle.ViewModel
import com.jddev.simpletouch.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationUiViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    fun showSimpleNotification() {
        notificationRepository.showSimpleNotification()
    }

    fun updateNotification() {
        notificationRepository.updateSimpleNotification()
    }

    fun cancelNotification() {
        notificationRepository.cancelSimpleNotification()
    }
}