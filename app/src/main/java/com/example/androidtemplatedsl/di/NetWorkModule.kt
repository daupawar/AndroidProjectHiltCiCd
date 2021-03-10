package com.example.androidtemplatedsl.di

import com.example.androidtemplatedsl.data.remote.RestService
import com.example.androidtemplatedsl.data.remote.RestServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetWorkModule {

    @Singleton
    @Provides
    fun provideRestService() : RestService {
        return RestServiceFactory.makeRestService()
    }
}
