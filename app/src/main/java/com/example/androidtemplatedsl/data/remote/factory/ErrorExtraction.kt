package com.example.androidtemplatedsl.data.remote.factory

import okhttp3.ResponseBody
import okio.IOException
import retrofit2.Converter
import retrofit2.HttpException

internal const val UNKNOWN_ERROR_RESPONSE_CODE = 520

internal fun <E : Any> HttpException.extractFromHttpException(
    errorConverter: Converter<ResponseBody, E>
): NetworkResponse.Error<E> {
    val error = response()?.errorBody()
    val responseCode = response()?.code() ?: UNKNOWN_ERROR_RESPONSE_CODE
    val errorBody = when {
        error == null -> null // No error content available
        error.contentLength() == 0L -> null // Error content is empty
        else -> try {
            // There is error content present, so we should try to extract it
            errorConverter.convert(error)
        } catch (e: Exception) {
            // If unable to extract content, return with a null body and don't parse further
            return NetworkResponse.Error(
                body = null,
                code = responseCode,
                type = NetworkResponse.ErrorType.SERVER,
                error = null
            )
        }
    }
    return NetworkResponse.Error(body = errorBody,code =  responseCode,type = NetworkResponse.ErrorType.SERVER,error = null)
}

internal fun <S : Any, E : Any> Throwable.extractNetworkResponse(
    errorConverter: Converter<ResponseBody, E>
): NetworkResponse<S, E> {
    return when (this) {
        is IOException -> NetworkResponse.Error(body = null,code =  0,type = NetworkResponse.ErrorType.NETWORK,error = this)
        is HttpException -> extractFromHttpException<E>(errorConverter)
        else -> NetworkResponse.Error(body = null,code =  0,type = NetworkResponse.ErrorType.UNKNOWN,error = this)
    }
}