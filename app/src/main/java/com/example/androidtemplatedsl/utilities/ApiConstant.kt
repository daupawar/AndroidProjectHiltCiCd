package com.example.androidtemplatedsl.utilities

import com.example.androidtemplatedsl.BuildConfig


const val DEV = "dev"
const val STAGING = "staging"
const val BETA = "beta"
const val PROD = "production"

const val SOCKET_TIME_OUT_EXCEPTION = "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
const val UNKNOWN_NETWORK_EXCEPTION = "An unexpected error has occurred. Please check your network connection and try again."
const val CONNECT_EXCEPTION = "Could not connect to the server. Please check your internet connection and try again."
const val UNKNOWN_HOST_EXCEPTION = "Couldn't connect to the server at the moment. Please try again in a few minutes."

class ApiConstant {
    companion object {
        val BASE_URL = when (BuildConfig.FLAVOR) {
            DEV -> "https://reqres.in/"
            STAGING -> ""
            BETA -> ""
            PROD -> ""
            else -> ""
        }

        val BASE_FILE_SERVER_URL = "${BASE_URL}FileServer/Get?id="

        val BASE_AUTH_URL = when (BuildConfig.FLAVOR) {
            DEV -> "/"
            STAGING -> "/"
            BETA -> "/"
            PROD -> "/"
            else -> ""
        }
    }


}