package com.example.demokotlinflow.data.base.remote

sealed class Resource<T>(
    val data: Any? = null,
    val message: String? = null,
    val isFromDb: Boolean = false
) {
    class Success<T>(data: Any?, isFromDb: Boolean = false) : Resource<T>(data, isFromDb = isFromDb)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}