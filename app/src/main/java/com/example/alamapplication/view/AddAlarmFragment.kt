package com.example.alamapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alamapplication.R
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.util.TimePickerUtil.getTimePickerHour
import com.example.alamapplication.util.TimePickerUtil.getTimePickerMinute
import com.example.alamapplication.viewmodel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddAlarmFragment : Fragment() {

    lateinit var buttonSave: Button
    lateinit var timePicker: TimePicker
    lateinit var viewModel: AlarmViewModel
    lateinit var monday: CheckBox
    lateinit var tusday: CheckBox
    lateinit var wednesday: CheckBox
    lateinit var thursday: CheckBox
    lateinit var friday: CheckBox
    lateinit var sarturday: CheckBox
    lateinit var sunday: CheckBox
    lateinit var recurring: CheckBox
    lateinit var linearLayoutRecurringOptions: LinearLayout
    lateinit var editTextTitle: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_alarm, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)

        buttonSave = view.findViewById(R.id.buttonSave)
        timePicker = view.findViewById(R.id.timePicker)
        monday = view.findViewById(R.id.checkBoxMonday)
        tusday = view.findViewById(R.id.checkBoxTuesday)
        wednesday = view.findViewById(R.id.checkBoxWednesday)
        thursday = view.findViewById(R.id.checkBoxThursday)
        friday = view.findViewById(R.id.checkBoxFriday)
        sarturday = view.findViewById(R.id.checkBoxSartuday)
        sunday = view.findViewById(R.id.checkBoxSunday)
        recurring = view.findViewById(R.id.checkBoxRecurring)
        linearLayoutRecurringOptions = view.findViewById(R.id.linearLayoutRecurringOptions)
        editTextTitle = view.findViewById(R.id.editTextTitle)


        recurring.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                linearLayoutRecurringOptions.visibility = View.VISIBLE
            } else {
                linearLayoutRecurringOptions.visibility = View.GONE
            }
        }

        viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]

        buttonSave.setOnClickListener {
            scheduleAlarm()
            findNavController().navigate(R.id.action_addAlarmFragment_to_alarmFragment)
        }

        return view
    }

    private fun scheduleAlarm() {
        val alarmId: Int = Random().nextInt(Int.MAX_VALUE)
        val alarm = Alarm(
            alarmId,
            getTimePickerHour(timePicker),
            getTimePickerMinute(timePicker),
            editTextTitle.text.toString(),
            System.currentTimeMillis(),
            true,
            recurring.isChecked,
            monday.isChecked,
            tusday.isChecked,
            wednesday.isChecked,
            thursday.isChecked,
            friday.isChecked,
            sarturday.isChecked,
            sunday.isChecked
        )
        viewModel.addAlarm(alarm)
        alarm.schedule(requireContext().applicationContext)
    }
}