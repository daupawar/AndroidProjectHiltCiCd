package com.example.androidtemplatedsl.di

import com.example.androidtemplatedsl.data.coroutines.DispatcherProvider
import com.example.androidtemplatedsl.data.local.AppDao
import com.example.androidtemplatedsl.data.local.LocalDataSource
import com.example.androidtemplatedsl.data.local.LocalDataSourceImpl
import com.example.androidtemplatedsl.data.remote.RemoteDataSource
import com.example.androidtemplatedsl.data.remote.RemoteDataSourceImpl
import com.example.androidtemplatedsl.data.remote.RestService
import com.example.androidtemplatedsl.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {

    @Provides
    fun provideRemoteDataSource(apiService: RestService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }


    /// Provide Local Data Sources ///
    @Provides
    fun provideLocalDataSource(appDao: AppDao): LocalDataSource {
        return LocalDataSourceImpl(appDao)
    }

    /// Provide repositories ///
    @Singleton
    @Provides
    fun provideDataRepository(
        dispatcherProvider: DispatcherProvider,
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): DataRepository {
        return DataRepositoryImpl(dispatcherProvider, remoteDataSource, localDataSource)
    }

}