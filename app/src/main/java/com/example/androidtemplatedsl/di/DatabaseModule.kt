package com.example.androidtemplatedsl.di

import android.content.Context
import com.example.androidtemplatedsl.data.local.AppDao
import com.example.androidtemplatedsl.data.local.AppDatabase
import com.example.androidtemplatedsl.data.local.DbFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return DbFactory.createDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAppDao(footballDatabase: AppDatabase):
            AppDao = footballDatabase.appDao()
}
