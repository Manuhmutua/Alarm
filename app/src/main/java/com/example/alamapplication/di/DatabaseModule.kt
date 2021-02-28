package com.example.alamapplication.di

import android.content.Context
import androidx.room.Room
import com.example.alamapplication.dao.AlarmDao
import com.example.alamapplication.source.databse.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(appContext,
            Database::class.java, "_Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(database: Database): AlarmDao {
        return database.alarmDao()
    }
}