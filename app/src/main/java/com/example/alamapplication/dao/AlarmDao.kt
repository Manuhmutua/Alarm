package com.example.alamapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.alamapplication.model.Alarm

@Dao
interface AlarmDao {
    @Insert
    fun insert(alarm: Alarm)

    @Query("DELETE FROM alarm_table")
    fun deleteAll()

    @Query("SELECT * FROM alarm_table ORDER BY created ASC")
    fun getAlarms(): LiveData<List<Alarm>>

    @Update
    fun update(alarm: Alarm)
}