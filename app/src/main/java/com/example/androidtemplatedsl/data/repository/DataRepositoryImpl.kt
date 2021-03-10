package com.example.androidtemplatedsl.data.repository

import com.example.androidtemplatedsl.data.coroutines.DispatcherProvider
import com.example.androidtemplatedsl.data.local.LocalDataSource
import com.example.androidtemplatedsl.data.remote.RemoteDataSource
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.di.DataRepository
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse

class DataRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider?,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :DataRepository {
    override suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError> {
        return remoteDataSource.getAllUsers()
    }
}