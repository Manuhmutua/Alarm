package com.example.alamapplication.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.FRIDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.MONDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.RECURRING
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.SATURDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.SUNDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.THURSDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.TITLE
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.TUESDAY
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver.Companion.WEDNESDAY
import com.example.alamapplication.util.DayUtil
import java.util.*


@Entity(tableName = "alarm_table")
data class Alarm(val id: Int)