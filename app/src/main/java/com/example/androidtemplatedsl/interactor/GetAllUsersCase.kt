package com.example.androidtemplatedsl.interactor

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.di.DataRepository
import com.example.androidtemplatedsl.interactor.base.BaseUseCase
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse
import javax.inject.Inject

class GetAllUsersCase @Inject constructor(
    private val repository: DataRepository
): BaseUseCase<Void, NetworkResponse<UserResponse, GenericError>>() {
    override suspend fun invoke(parameters: Void?): NetworkResponse<UserResponse, GenericError> {
        return repository.getAllUsers()
    }
}