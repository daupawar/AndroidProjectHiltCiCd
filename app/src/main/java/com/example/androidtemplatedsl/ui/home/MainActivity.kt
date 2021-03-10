package com.example.androidtemplatedsl.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.example.androidtemplatedsl.R
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.usersLiveData.observe(this) { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    Timber.d("Loading")
                }
                is NetworkResponse.Error -> {
                    Timber.e(result.error)
                }
                is NetworkResponse.Success -> {
                    Timber.d("Success ${result.body.data}")
                }
            }
        }
    }

    fun callApi(v:View){
        viewModel.getAllUsers()
    }
}