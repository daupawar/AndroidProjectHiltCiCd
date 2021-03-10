package com.example.androidtemplatedsl.data.remote

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse
import retrofit2.http.GET

interface RestService {

    @GET("api/users?page=2")
    suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError>


}