package com.example.hospitalproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object{
        private const val CHANNEL_NAME = "my_channel"
        private const val CHANNEL_ID = "channel_id"
        private const val NOTIFY_ID = 1
    }

    private fun createNotification(title:String,text:String): Notification {
        val intent = Intent(this,RegistrationActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val builder = Notification.Builder(this, CHANNEL_ID)
        builder.setContentIntent(contentIntent)
            .setSmallIcon(R.drawable.ic_profile)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(text)
        return builder.build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.mainBottomNavigationMenu)
        val navController = findNavController(R.id.mainContainerView)
        bottomNavigationView.setupWithNavController(navController)
        if (this.intent?.getStringExtra("N")=="N"){
            val sdf = SimpleDateFormat("HH:mm",Locale.getDefault())
            //Создаю канал уведомлений
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "send notifications about enlists"
            val notifyManager = getSystemService(NotificationManager::class.java)
            notifyManager.createNotificationChannel(channel)
            //Cоздаю уведомление
            val notification = createNotification("Запись к врачу","В ${sdf.format(System.currentTimeMillis())}")
            notifyManager.notify(NOTIFY_ID,notification)
        }
    }
}
