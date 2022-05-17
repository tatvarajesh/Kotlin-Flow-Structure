package com.example.demokotlinflow.data.base

import com.example.demokotlinflow.data.user.remote.request.LoginRequest
import com.example.demokotlinflow.data.user.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface BaseApi {
//    @POST("api/v1/verify_cus")
//    suspend fun callLoginCustomer(@Body loginRequest: LoginRequest?): LoginResponse
}