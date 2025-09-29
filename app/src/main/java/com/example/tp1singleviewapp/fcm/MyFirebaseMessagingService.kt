package com.example.tp1singleviewapp.fcm

import android.util.Log
import com.example.tp1singleviewapp.model.Utils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService()  {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM messages here
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if the message contains data
        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)

            var title = remoteMessage.data["title"]
            var body = remoteMessage.data["body"]
            var imageUrl = remoteMessage.data["image"]

            if (title == null) title = "empty Title"
            if (body == null) body = "empty Body"
            if (imageUrl == null) imageUrl = Utils.IMAGE_WINDOWS

            Utils.showNotification(
                applicationContext,
                title,
                body,
                imageUrl
            )
        }

        // Check if the message contains a notification payload
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        // Handle new or refreshed FCM registration token
        Log.d(TAG, "Refreshed token: $token")
        // You may want to send this token to your server for further use
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}