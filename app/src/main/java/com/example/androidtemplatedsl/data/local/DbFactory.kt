package com.example.androidtemplatedsl.data.local

import android.content.Context
import androidx.room.Room
import com.example.androidtemplatedsl.utilities.DATABASE_NAME

object DbFactory {
    fun createDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}