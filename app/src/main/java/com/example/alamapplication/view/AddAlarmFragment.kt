package com.example.alamapplication.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.alamapplication.R
import com.example.alamapplication.broadcastreceiver.AlarmBroadcastReceiver
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.viewmodel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAlarmFragment : Fragment() {

    lateinit var buttonSave: Button
    lateinit var timePicker: TimePicker
    lateinit var viewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_alarm, container, false)
        buttonSave = view.findViewById(R.id.buttonSave)
        timePicker = view.findViewById(R.id.timePicker)

        viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]

        buttonSave.setOnClickListener {
            val a = Alarm(
                1,
                1,
                1,
                "",
                1,
                false,
                recurring = false,
                monday = false,
                tuesday = false,
                thursday = false,
                friday = false,
                wednesday = false,
                saturday = false,
                sunday = false
            )

            viewModel.addAlarm(a)
            viewModel.getAlarms()?.observe(viewLifecycleOwner) { alarms ->
                Toast.makeText(view.context, "Some Text: " + alarms[0].monday, Toast.LENGTH_LONG)
                    .show()
            }
//            val calendar: Calendar = Calendar.getInstance()
//            if (Build.VERSION.SDK_INT >= 23) {
//                calendar.set(
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    timePicker.hour,
//                    timePicker.minute,
//                    0
//                )
//            } else {
//                calendar.set(
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    timePicker.currentHour,
//                    timePicker.currentMinute, 0
//                )
//            }
//            setAlarm(calendar.timeInMillis)
        }

        return view
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager =
            context?.applicationContext?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context?.applicationContext, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context?.applicationContext, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context?.applicationContext, "Alarm is set", Toast.LENGTH_SHORT).show()
    }

}