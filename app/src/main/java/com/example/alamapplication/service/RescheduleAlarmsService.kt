package com.example.alamapplication.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.observe
import com.example.alamapplication.viewmodel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RescheduleAlarmsService : LifecycleService() {

    lateinit var viewModel: AlarmViewModel

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        viewModel.getAlarms()?.observe(this) { alarms ->
            for (a in alarms) {
                if (a.started) {
                    a.schedule(applicationContext)
                }
            }
        }

        return Service.START_STICKY
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}