package com.example.alamapplication.di

import android.app.Application
import androidx.room.Room
import com.example.alamapplication.dao.AlarmDao
import com.example.alamapplication.source.databse.Database
import dagger.Provides
import javax.inject.Singleton

object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(application: Application?): Database {
        return Room.databaseBuilder(
            application!!,
            Database::class.java, "Alarm Database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePokeDao(database: Database): AlarmDao? {
        return database.alarmDao()
    }
}