package com.example.androidtemplatedsl.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anzu.evepatient.ui.base.BaseViewModel
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.GetAllUsersCase
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val getAllUsers: GetAllUsersCase) :
    BaseViewModel() {

    val usersLiveData = MutableLiveData<NetworkResponse<UserResponse, GenericError>>()

    fun getAllUsers(){
        usersLiveData.postValue(NetworkResponse.Loading())
        viewModelScope.launch {
            usersLiveData.postValue(getAllUsers.invoke(null))
        }
    }
}