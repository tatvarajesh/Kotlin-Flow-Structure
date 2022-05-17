package com.example.demokotlinflow.data.login.remote

import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.data.login.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginApiService {
    @POST("api/v1/verify_cus")
    suspend fun callLoginCustomer(@Body loginRequest: LoginRequest?): LoginResponse
}