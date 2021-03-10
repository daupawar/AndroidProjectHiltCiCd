package com.example.androidtemplatedsl.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidtemplatedsl.data.TestData
import com.example.androidtemplatedsl.data.TestLocalDataSource
import com.example.androidtemplatedsl.data.TestRemoteDataSource
import com.example.androidtemplatedsl.data.remote.factory.data
import com.example.androidtemplatedsl.data.repository.DataRepositoryImpl
import com.example.androidtemplatedsl.interactor.GetAllUsersCase
import com.example.androidtemplatedsl.test.util.LiveDataTestUtil
import com.example.androidtemplatedsl.test.util.MainCoroutineRule
import com.example.androidtemplatedsl.test.util.observeForTesting
import com.example.androidtemplatedsl.test.util.runBlockingTest
import com.example.androidtemplatedsl.ui.home.HomeViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val defaultRepository = DataRepositoryImpl(null, TestRemoteDataSource, TestLocalDataSource)

    @Test
    fun testGetAllUser()= coroutineRule.runBlockingTest {
        val useCase = GetAllUsersCase(defaultRepository)

        val viewModel = HomeViewModel(useCase)
        viewModel.getAllUsers()

        viewModel.usersLiveData.observeForTesting {
            assertEquals(
                TestData.apiResponse.body.data,
                LiveDataTestUtil.getValue(viewModel.usersLiveData)?.data?.data
            )
        }
    }
}