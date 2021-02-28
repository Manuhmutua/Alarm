package com.example.alamapplication.model

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
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
data class Alarm(
    @PrimaryKey var alarmId: Int,
    var hour: Int,
    var minute: Int,
    var title: String,
    var created: Long,
    var started: Boolean,
    var recurring: Boolean,
    var monday: Boolean,
    var tuesday: Boolean,
    var wednesday: Boolean,
    var thursday: Boolean,
    var friday: Boolean,
    var saturday: Boolean,
    var sunday: Boolean
) {
    @SuppressLint("DefaultLocale")
    fun schedule(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra(RECURRING, recurring)
        intent.putExtra(MONDAY, monday)
        intent.putExtra(TUESDAY, tuesday)
        intent.putExtra(WEDNESDAY, wednesday)
        intent.putExtra(THURSDAY, thursday)
        intent.putExtra(FRIDAY, friday)
        intent.putExtra(SATURDAY, saturday)
        intent.putExtra(SUNDAY, sunday)
        intent.putExtra(TITLE, title)
        val alarmPendingIntent =
            PendingIntent.getBroadcast(context, alarmId, intent, 0)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // if alarm time has already passed, increment day by 1
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        }
        if (!recurring) {
            var toastText: String? = null
            try {
                toastText = String.format(
                    "One Time Alarm %s scheduled for %s at %02d:%02d",
                    title,
                    DayUtil.toDay(calendar.get(Calendar.DAY_OF_WEEK)),
                    hour,
                    minute,
                    alarmId
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmPendingIntent
            )
        } else {
            val toastText = java.lang.String.format(
                "Recurring Alarm %s scheduled for %s at %02d:%02d",
                title,
                getRecurringDaysText(),
                hour,
                minute,
                alarmId
            )
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            val RUN_DAILY = 24 * 60 * 60 * 1000.toLong()
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                RUN_DAILY,
                alarmPendingIntent
            )
        }
        started = true
    }

    fun cancelAlarm(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val alarmPendingIntent =
            PendingIntent.getBroadcast(context, alarmId, intent, 0)
        alarmManager.cancel(alarmPendingIntent)
        started = false
        val toastText =
            String.format("Alarm cancelled for %02d:%02d with id %d", hour, minute, alarmId)
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
        Log.i("cancel", toastText)
    }

    private fun getRecurringDaysText(): String? {
        if (!recurring) {
            return null
        }
        var days = ""
        if (monday) {
            days += "Mo "
        }
        if (tuesday) {
            days += "Tu "
        }
        if (wednesday) {
            days += "We "
        }
        if (thursday) {
            days += "Th "
        }
        if (friday) {
            days += "Fr "
        }
        if (saturday) {
            days += "Sa "
        }
        if (sunday) {
            days += "Su "
        }
        return days
    }

}