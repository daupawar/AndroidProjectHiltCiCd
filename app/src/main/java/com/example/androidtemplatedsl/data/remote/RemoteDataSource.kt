package com.example.androidtemplatedsl.data.remote

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse

interface RemoteDataSource {
    suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError>
}