package com.kumail.popularshop.network

import retrofit2.Response

/**
 * Created by kumailhussain on 15/10/2021.
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()

                if (body == null || response.code() == 204) {
                    Empty()
                } else {
                    Success(body)
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if (message.isNullOrEmpty()) {
                    response.message()
                } else {
                    message
                }
                NetworkError(errorMessage)
            }
        }
    }

    data class Success<T>(val data: T) : ApiResponse<T>()
    class Empty<T> : ApiResponse<T>()
    data class NetworkError<T>(val errorResponse: String) : ApiResponse<T>()
    data class ExceptionError<T>(val errorResponse: Exception) : ApiResponse<T>()
}