package com.example.tp1singleviewapp.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.tp1singleviewapp.R
import java.net.URL

object Utils {
    const val DB_NAME = "concerts"
    const val IMAGE_WINDOWS = "https://images.ctfassets.net/hrltx12pl8hq/28ECAQiPJZ78hxatLTa7Ts/2f695d869736ae3b0de3e56ceaca3958/free-nature-images.jpg?fit=fill&w=1200&h=630"
    fun showNotification(
        context: Context,
        title: String,
        body: String,
        imageUrl: String
    ) {
        val channelId = "default_channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create channel (for Android 8+)
        val channel = NotificationChannel(
            channelId,
            "Default Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.rennes) // put your own icon
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // If image is provided → BigPicture style
        if (!imageUrl.isNullOrEmpty()) {
            try {
                val bitmap = BitmapFactory.decodeStream(URL(imageUrl).openConnection().getInputStream())
                builder.setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(bitmap)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Launch notification
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}