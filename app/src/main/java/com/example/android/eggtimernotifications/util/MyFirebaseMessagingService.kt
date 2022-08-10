package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "Firebase Service"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed Token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        remoteMessage?.data?.let {
            Log.d(TAG, "Data payload: ${remoteMessage.data}")
        }

        // Send a notification either the app in foreground or background
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message notification body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(messageBody: String) {
        val notificationManager =
            ContextCompat.getSystemService(
                applicationContext,
                NotificationManager::class.java
            ) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

}