package com.example.alamapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.repository.AlarmRepository
import javax.inject.Inject


class AlarmViewModel @Inject constructor(
    @Inject private var repository: AlarmRepository
) : ViewModel() {
    private var tripList: LiveData<List<Alarm>>? = null

    fun addAlarm(alarm: Alarm) {
        repository.insertAlarm(alarm)
    }

    fun getAlarms(): LiveData<List<Alarm?>?>? {
        return repository.getAlarms
    }

}