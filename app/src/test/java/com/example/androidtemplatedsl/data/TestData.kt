package com.example.androidtemplatedsl.data

import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.interactor.response.User
import com.example.androidtemplatedsl.interactor.response.UserResponse


/**
 * Test data for unit tests.
 */
object TestData {
    val user1 = User(
        id = 7,
        email = "michael.lawson@reqres.in",
        first_name = "Michael",
        last_name = "Lawson",
        avatar = "https://reqres.in/img/faces/7-image.jpg"
    )

    val user2 = User(
        id = 8,
        email = "lindsay.ferguson@reqres.in",
        first_name = "Lindsay",
        last_name = "Ferguson",
        avatar = "https://reqres.in/img/faces/8-image.jpg"
    )


    val user3 = User(
        id = 9,
        email = "tobias.funke@reqres.in",
        first_name = "Tobias",
        last_name = "Funke",
        avatar = "https://reqres.in/img/faces/9-image.jpg"
    )

    val user4 = User(
        id = 10,
        email = "byron.fields@reqres.in",
        first_name = "Byron",
        last_name = "Fields",
        avatar = "https://reqres.in/img/faces/10-image.jpg"
    )

    val user5 = User(
        id = 11,
        email = "george.edwards@reqres.in",
        first_name = "George",
        last_name = "Edwards",
        avatar = "https://reqres.in/img/faces/11-image.jpg"
    )

    val user6 = User(
        id = 12,
        email = "rachel.howell@reqres.in",
        first_name = "Rachel",
        last_name = "Howell",
        avatar = "https://reqres.in/img/faces/12-image.jpg"
    )

    private val userList = listOf(user1, user2, user3, user4, user5, user6)

    private val userResponse = UserResponse(
        data = userList,
        page = 2,
        per_page = 6,
        total = 12,
        total_pages = 2
    )

    val apiResponse = NetworkResponse.Success(userResponse)


    private val userResponseError = UserResponse(
        data = userList,
        page = 3,
        per_page = 3,
        total = 3,
        total_pages = 3
    )

    val apiResponseError = NetworkResponse.Success(userResponseError)

}
