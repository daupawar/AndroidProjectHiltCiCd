package com.example.androidtemplatedsl.data.remote

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse


class RemoteDataSourceImpl(private val apiService: RestService) :RemoteDataSource {
    override suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError> {
        return apiService.getAllUsers()
    }
}