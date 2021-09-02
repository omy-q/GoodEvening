package com.example.goodevening.superview.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.goodevening.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessagingService : FirebaseMessagingService() {

    private val PUSH_KEY_TITLE = "title"
    private val PUSH_KEY_MESSAGE = "message"
    private val CHANNEL_ID = "first_channel"

    private val NOTIFICATION_ID = 1

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data
        if(data.isNotEmpty()) {
            checkData(data.toMap())
        }
    }

    private fun checkData(data: Map<String, String>) {
        val title = data[PUSH_KEY_TITLE]
        val message = data[PUSH_KEY_MESSAGE]
        if (!title.isNullOrBlank() && !message.isNullOrBlank()){
            pushMessage(title, message)
        }

    }

    private fun pushMessage(title: String, message: String) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle(title)
            setContentText(message)
            setSmallIcon(R.drawable.ic_push_message)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel(notificationManager)
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManager) {
        val name = "news"
        val descriptionText = "the messages notify about the news in the cinema"
        val channel = NotificationChannel(CHANNEL_ID, name,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(token: String) {
        Log.d("token", "$token")
    }
}