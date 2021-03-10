package com.example.androidtemplatedsl.usecase

import com.example.androidtemplatedsl.data.TestData
import com.example.androidtemplatedsl.data.TestLocalDataSource
import com.example.androidtemplatedsl.data.TestRemoteDataSource
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.data.repository.DataRepositoryImpl
import com.example.androidtemplatedsl.di.DataRepository
import com.example.androidtemplatedsl.interactor.GetAllUsersCase
import com.example.androidtemplatedsl.interactor.response.GenericError
import com.example.androidtemplatedsl.interactor.response.UserResponse
import com.example.androidtemplatedsl.test.util.MainCoroutineRule
import com.example.androidtemplatedsl.test.util.runBlockingTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class UserListUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    fun loadAllUserSuccess() = coroutineRule.runBlockingTest {
        val useCase = GetAllUsersCase(successfulRepository)
        val result = useCase.invoke() as NetworkResponse.Success

        assertThat(
            result,
            `is`(equalTo(TestData.apiResponse))
        )
    }

    @Test
    fun userLoadedUnsuccessfully() = coroutineRule.runBlockingTest {
        val useCase = GetAllUsersCase(unsuccessfulUserRepository)
        val result = useCase.invoke()
        assertTrue(result is NetworkResponse.Error)
    }


    private val successfulRepository = DataRepositoryImpl(
        null, TestRemoteDataSource, TestLocalDataSource
    )


    private val unsuccessfulUserRepository = object : DataRepository {
        override suspend fun getAllUsers(): NetworkResponse<UserResponse, GenericError> {
            return NetworkResponse.Error(
                body = null,
                code = 0,
                type = NetworkResponse.ErrorType.SERVER,
                error = Exception()
            )
        }
    }
}