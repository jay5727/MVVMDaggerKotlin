package com.jay.mvvmdaggerkotlin.data

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
 * ApiResponse is a simple wrapper around the Retrofit2.Call class that convert responses to instances of LiveData.
 *
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {

            val errorMessage = when (error) {
                is HttpException -> "An error has occurred: ${error.code()} Please try again."
                is SocketTimeoutException -> "A timeout error has occurred, please check your internet connection and try again"
                is IOException -> "An IO error has occurred, most likely a network issue. Please check your internet connection and try again"
                else -> {
                    error.message
                }
            }

            return ApiErrorResponse(errorMessage ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                //handle other specific cases for future using HttpURLConnection class...
                //TODO : HttpURLConnection.HTTP_UNAUTHORIZED == response.code()
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()