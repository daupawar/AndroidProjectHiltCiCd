package com.example.androidtemplatedsl.networking

import com.example.androidtemplatedsl.data.remote.RestService
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponse
import com.example.androidtemplatedsl.data.remote.factory.NetworkResponseAdapterFactory
import com.example.androidtemplatedsl.data.remote.factory.StringConverterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import io.kotlintest.matchers.types.shouldBeTypeOf
import io.kotlintest.shouldBe
import okhttp3.mockwebserver.SocketPolicy
import org.hamcrest.CoreMatchers.`is`

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserApiTest {

//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var service: RestService
//
//    private lateinit var mockWebServer: MockWebServer
//
//    private val dispatcher = TestCoroutineDispatcher()


    private lateinit var server: MockWebServer
    private lateinit var executor: ExecutorService
    private lateinit var retrofit: Retrofit
    private lateinit var service: RestService


    @Before
    fun createService() {
        server = MockWebServer()
        executor = Executors.newSingleThreadExecutor()
        retrofit = Retrofit.Builder()
            .baseUrl(server.url("/suspend/"))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(StringConverterFactory())
            .callbackExecutor(executor)
            .build()
        service = retrofit.create(RestService::class.java)
    }

    @After
    fun stopService() {
        server.shutdown()
    }

    @Test
    fun `successful response test`() {
        val responseBody=parseResponseFromFile("Users.json")
        enqueueResponse(responseBody)

        val response = runBlocking {
            service.getAllUsers()
        }

        with(response) {
            shouldBeTypeOf<NetworkResponse.Success<String>>()
            this as NetworkResponse.Success
            body shouldBe responseBody
        }
    }

    @Test
    fun `empty response test`() {
        val responseCode = 404
        server.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
        )
        val response = runBlocking { service.getAllUsers() }

        with (response) {
            shouldBeTypeOf<NetworkResponse.Error<String>>()
            this as NetworkResponse.Error
            code shouldBe responseCode
            body.toString().isBlank() shouldBe true
        }
    }

    @Test
    fun `network error test`() {
        server.enqueue(MockResponse().apply { socketPolicy = SocketPolicy.DISCONNECT_AFTER_REQUEST })
        val response = runBlocking { service.getAllUsers() }
        with (response) {
            shouldBeTypeOf<NetworkResponse.Error<String>>()
        }
    }

    @Test
    fun `unsuccessful response with empty body`() {
        val successResponseCode = 400
        server.enqueue(MockResponse().apply {
            setResponseCode(successResponseCode)
        })

        val response = runBlocking { service.getAllUsers() }

        with(response) {
            shouldBeTypeOf<NetworkResponse.Error<String>>()
            this as NetworkResponse.Error
            body shouldBe ""
            code shouldBe 400
        }
    }

    @Test
    fun getPostsTest() {
        val responseBody=parseResponseFromFile("Users.json")
        enqueueResponse(responseBody)

        val response = runBlocking {
            service.getAllUsers() as NetworkResponse
        }
        assertThat(response, notNullValue())
//        assertThat(response.data?.size, `is`(6))
//            assertThat(posts?.data?.get(0)?.email, `is`("michael.lawson@reqres.in"))
    }

    private fun enqueueResponse(responseBody: String) {
        server.enqueue(
            MockResponse()
                .setBody(responseBody)
                .setResponseCode(200)
                .setHeader("TEST","test")
        )
    }

    private fun parseResponseFromFile(fileName: String, headers: Map<String, String> = emptyMap()): String {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        return source.readString(Charsets.UTF_8)
    }
}