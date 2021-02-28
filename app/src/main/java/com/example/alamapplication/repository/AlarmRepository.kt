package com.example.alamapplication.repository

import androidx.lifecycle.LiveData
import com.example.alamapplication.dao.AlarmDao
import com.example.alamapplication.model.Alarm
import javax.inject.Inject

class AlarmRepository @Inject constructor(var alarmDao: AlarmDao) {

    val getAlarms: LiveData<List<Alarm>>?
        get() = alarmDao.getAlarms()

    fun insertAlarm(alarm: Alarm) {
        alarmDao.insert(alarm)
    }

    fun updateAlarm(alarm: Alarm) {
        alarmDao.update(alarm)
    }

    fun deleteAll() {
        alarmDao.deleteAll()
    }

}