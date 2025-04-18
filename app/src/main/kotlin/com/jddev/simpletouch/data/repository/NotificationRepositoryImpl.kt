package com.jddev.simpletouch.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.jddev.simpletouch.R
import com.jddev.simpletouch.domain.repository.NotificationRepository
import com.jddev.simpletouch.ui.MainActivity
import timber.log.Timber

class NotificationRepositoryImpl(
    private val context: Context,
) : NotificationRepository {

    private val channelChargingNotification = NotificationChannel(
        CHANNEL_CHARGING_NOTIFICATION,
        "Intelligent Charge limit",
        NotificationManager.IMPORTANCE_HIGH
    ).also {
        it.enableVibration(true)
    }
    private val channelLimitNotification = NotificationChannel(
        CHANNEL_LIMIT_NOTIFICATION,
        "Intelligent Charge only display",
        NotificationManager.IMPORTANCE_MIN
    ).also {
        it.enableVibration(true)
    }

    private val channelSimpleNotification = NotificationChannel(
        CHANNEL_SIMPLE_NOTIFICATION,
        "Simple Notification Demo",
        NotificationManager.IMPORTANCE_HIGH
    ).also {
        it.enableVibration(true)
    }

    init {
        (context.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager).also {
            it.createNotificationChannel(channelChargingNotification)
            it.createNotificationChannel(channelLimitNotification)
            it.createNotificationChannel(channelSimpleNotification)
        }
    }

    @SuppressLint("MissingPermission", "NotificationPermission")
    override fun showChargeLimitNotification() {
        Timber.d("showChargeLimitNotification")
        if (isNotificationVisible(CHANNEL_CHARGING_NOTIFICATION_ID)) {
            Timber.d("Notification already visible")
            return
        }
        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "https://jddev.com/intelligent_charging/".toUri(),
            context,
            MainActivity::class.java
        )
        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)!!
        }
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_CHARGING_NOTIFICATION)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(R.drawable.ic_outline_battery_saver)
            .setContentTitle("Charge smartly with intelligent charging")
            .setContentText("Enable battery control to prevent deterioration and prolong life.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("After charging to 90%, it stops charging and switches to direct power supply.")
            )
            .setContentIntent(clickPendingIntent)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        notificationBuilder.addAction(
            0,
            "Settings",
            PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
        notificationBuilder.setColor(ContextCompat.getColor(context, R.color.teal_700))
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                // ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                //                                        grantResults: IntArray)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Timber.e("Notification permission not granted")
                return@with
            }
            // notificationId is a unique int for each notification that you must define.
            notify(CHANNEL_CHARGING_NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    override fun cancelChargeLimitNotification() {
        NotificationManagerCompat.from(context).cancel(CHANNEL_CHARGING_NOTIFICATION_ID)
    }

    @SuppressLint("MissingPermission", "NotificationPermission")
    override fun showSimpleNotification(title: String?) {
        if (isNotificationVisible(CHANNEL_SIMPLE_NOTIFICATION_ID) && title.isNullOrEmpty()) {
            Timber.d("Notification already visible")
            return
        }
        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "https://jddev.com/notificationUi/notificationUiDetail/simple_message=Coming from Notification".toUri(),
            context,
            MainActivity::class.java
        )
        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)!!
        }
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_SIMPLE_NOTIFICATION)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title ?: "Simple Notification")
            .setContentText("This is simple notification")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Click here to navigate to target screen")
            )
            .setContentIntent(clickPendingIntent)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        notificationBuilder.setColor(ContextCompat.getColor(context, R.color.teal_700))
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Timber.e("Notification permission not granted")
                return@with
            }
            notify(CHANNEL_SIMPLE_NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    override fun updateSimpleNotification() {
        showSimpleNotification("Update Simple Notification")
    }

    override fun cancelSimpleNotification() {
        NotificationManagerCompat.from(context).cancel(CHANNEL_SIMPLE_NOTIFICATION_ID)
    }

    private fun isNotificationVisible(notificationId: Int): Boolean {
        val mNotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        val notifications = mNotificationManager!!.activeNotifications
        for (notification in notifications) {
            if (notification.id == notificationId) {
                return true
            }
        }
        return false
    }

    companion object {
        const val CHANNEL_CHARGING_NOTIFICATION = "notification_channel_charging"
        const val CHANNEL_LIMIT_NOTIFICATION = "notification_channel_limit"
        const val CHANNEL_SIMPLE_NOTIFICATION = "notification_channel_simple"
        const val CHANNEL_SIMPLE_NOTIFICATION_ID = 0x198
        const val CHANNEL_CHARGING_NOTIFICATION_ID = 0x199
    }
}