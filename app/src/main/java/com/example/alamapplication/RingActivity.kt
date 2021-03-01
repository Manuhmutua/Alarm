package com.example.alamapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.service.AlarmService
import java.util.*

class RingActivity : AppCompatActivity() {

    lateinit var buttonDismiss: Button
    lateinit var buttonSnooze: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ring)

        buttonDismiss = findViewById(R.id.buttonDismiss)
        buttonSnooze = findViewById(R.id.buttonSnooze)

        buttonDismiss.setOnClickListener {
            val intentService =
                Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }

        buttonSnooze.setOnClickListener {

            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MINUTE, 10)

            val alarm = Alarm(
                Random().nextInt(Int.MAX_VALUE),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                "Snooze",
                System.currentTimeMillis(),
                started = true,
                recurring = false,
                monday = false,
                tuesday = false,
                wednesday = false,
                thursday = false,
                friday = false,
                saturday = false,
                sunday = false
            )

            alarm.schedule(applicationContext)

            val intentService = Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()

        }

    }
}