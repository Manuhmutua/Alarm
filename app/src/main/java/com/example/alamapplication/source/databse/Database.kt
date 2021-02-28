package com.example.alamapplication.source.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alamapplication.dao.AlarmDao
import com.example.alamapplication.model.Alarm

@Database(entities = [Alarm::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}