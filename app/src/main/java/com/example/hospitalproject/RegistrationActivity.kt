package com.example.hospitalproject

import android.annotation.SuppressLint
import android.app.*
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.example.hospitalproject.doctor.Doctor
import com.example.hospitalproject.fragments.notification.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var doctor:Doctor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        doctor = this.intent?.getParcelableExtra("D")!!

        val photo = findViewById<ImageView>(R.id.photoView)
        val surname = findViewById<TextView>(R.id.surnameView)
        val prof = findViewById<TextView>(R.id.profView)

        surname.text = doctor.name
        prof.text = doctor.profession
        if (doctor.photo.isNotBlank()){
            Glide.with(photo.context)
                .load(doctor.photo)
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(photo)
        } else {
            photo.setImageResource(R.drawable.ic_profile)
        }

        val c = Calendar.getInstance()
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        c.add(Calendar.DATE,1)
        datePicker.minDate = c.timeInMillis
        c.add(Calendar.DATE,6)
        datePicker.maxDate =c.timeInMillis

        val enlistButton = findViewById<Button>(R.id.enlist_button)
        datePicker.setOnDateChangedListener { _, year, month, day ->
            enlistButton.isEnabled = true
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, day)
            c.set(Calendar.MILLISECOND,0)
            c.set(Calendar.SECOND,0)
        }

        findViewById<Button>(R.id.enlist_button).setOnClickListener {
            //Получаю дату
            val alarmManager = getSystemService(AlarmManager::class.java)
            //Создаю информацию о будильнике
            c.set(Calendar.MILLISECOND,0)
            c.set(Calendar.SECOND,0)
            val alarmClockInfo = AlarmManager.AlarmClockInfo(c.timeInMillis,getAlarmInfoPendingIntent())
            //Создаю будильник, который по времени notifyDate вызывает notification
            alarmManager.setAlarmClock(alarmClockInfo,getAlarmActionPendingIntent())

            val sdf = SimpleDateFormat("HH:mm dd:MM:yyyy",Locale.getDefault())
            Toast.makeText(this,"Запись успешно оформлена на ${sdf.format(c.timeInMillis)}",Toast.LENGTH_SHORT).show()
        }

    }



    private fun getAlarmInfoPendingIntent(): PendingIntent {
        val alarmInfoIntent = Intent(this, RegistrationActivity::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        return PendingIntent.getActivity(
            this,
            0,
            alarmInfoIntent,
            FLAG_CANCEL_CURRENT
        )
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getAlarmActionPendingIntent():PendingIntent{
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("N","N")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(
            this,
            1,
            intent,
            FLAG_UPDATE_CURRENT
        )

    }
}