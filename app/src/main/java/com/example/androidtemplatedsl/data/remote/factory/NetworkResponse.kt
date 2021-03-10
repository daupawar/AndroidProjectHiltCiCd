package com.example.androidtemplatedsl.data.remote.factory

sealed class NetworkResponse<out T : Any, out U : Any> {

    data class Loading(val start: Int = 0) : NetworkResponse<Nothing, Nothing>()

    /**
     * A request that resulted in a response with a 2xx status code that has a body.
     */
    data class Success<T : Any>(
        val body: T
    ) : NetworkResponse<T, Nothing>()

    /**
     * A request that resulted in a response with a non-2xx status code.
     */
    data class Error<U : Any>(
        val body: U? = null,
        val error: Throwable?,
        val code: Int,
        val type: ErrorType
    ) : NetworkResponse<Nothing, U>()

    enum class ErrorType {
        SERVER,
        NETWORK,
        UNKNOWN
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val NetworkResponse<*,*>.succeeded
    get() = this is NetworkResponse.Success

fun <T : Any> NetworkResponse<T,Any>.successOr(fallback: T): T {
    return (this as? NetworkResponse.Success<*>)?.body as T? ?: fallback
}

val <T : Any> NetworkResponse<T,Any>.data: T?
    get() = (this as? NetworkResponse.Success)?.body