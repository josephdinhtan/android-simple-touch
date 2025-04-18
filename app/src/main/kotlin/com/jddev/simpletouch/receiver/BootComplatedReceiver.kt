package com.jddev.simpletouch.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jddev.simpletouch.ui.MainActivity
import timber.log.Timber

class BootCompletedReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Timber.d("Boot completed")
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(mainActivityIntent)

//            val serviceIntent = Intent(context, CoreArchForegroundService::class.java)
//            startForegroundService(context!!, serviceIntent)
        }
    }
}