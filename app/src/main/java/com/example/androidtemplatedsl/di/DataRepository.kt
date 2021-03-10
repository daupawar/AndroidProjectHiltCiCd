package com.example.androidtemplatedsl.di

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse

interface DataRepository {
    suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError>
}
