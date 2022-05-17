package com.example.demokotlinflow.data.logout.remote

import com.example.demokotlinflow.data.logout.remote.request.LogoutRequest
import com.example.demokotlinflow.data.logout.remote.response.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LogoutApiService {
    @POST("api/v1/verify_cus")
    suspend fun callLogoutCustomer(@Body logoutRequest: LogoutRequest?): LogoutResponse
}