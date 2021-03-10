package com.example.androidtemplatedsl.data.remote

import com.example.androidtemplatedsl.BuildConfig
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponseAdapterFactory
import com.example.androidtemplatedsl.utilities.ApiConstant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestServiceFactory {

    /**
     * makeRestService
     */
    fun makeRestService(): RestService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor()
        )

        return makeUserService(okHttpClient, makeGson())
    }

    /**
     * makeUserService
     */
    private fun makeUserService(okHttpClient: OkHttpClient, gson: Gson): RestService {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RestService::class.java)
    }


    /**
     * makeOkHttpClient
     */
    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun makeGson(): Gson {
        return GsonBuilder().serializeNulls().create()
    }

    /**
     * makeLoggingInterceptor
     */
    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
}