package com.example.androidtemplatedsl.data

import com.example.androidtemplatedsl.data.remote.RemoteDataSource
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse


object TestRemoteDataSource : RemoteDataSource {
    override suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError> = TestData.apiResponse
}
