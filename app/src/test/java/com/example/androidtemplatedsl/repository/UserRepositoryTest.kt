package com.example.androidtemplatedsl.repository

import com.example.androidtemplatedsl.data.TestData
import com.example.androidtemplatedsl.data.TestLocalDataSource
import com.example.androidtemplatedsl.data.TestRemoteDataSource
import com.example.androidtemplatedsl.data.remote.RemoteDataSource
import com.example.androidtemplatedsl.data.repository.DataRepositoryImpl
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException


class UserRepositoryTest {

    lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: DataRepositoryImpl

    fun setUp() {
        remoteDataSource = mock()
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(401)
        runBlocking {
            whenever(remoteDataSource.getAllUsers()).thenThrow(mockException)
            whenever(remoteDataSource.getAllUsers()).thenReturn(TestData.apiResponse)
        }
        repository = DataRepositoryImpl(
            null,
            TestRemoteDataSource, TestLocalDataSource
        )
    }
}