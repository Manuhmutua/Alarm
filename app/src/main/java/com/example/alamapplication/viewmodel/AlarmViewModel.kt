package com.example.alamapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.alamapplication.model.Alarm
import com.example.alamapplication.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val repository: AlarmRepository) : ViewModel() {
    private var tripList: LiveData<List<Alarm>>? = null

    fun getAlarms(): LiveData<List<Alarm>>? {
        return tripList
    }

    fun addAlarm(alarm: Alarm){
        repository.insertAlarm(alarm)
    }

    companion object {
        private const val TAG = "AlarmViewModel"
    }

    init {
        tripList = repository.getAlarms
    }
}