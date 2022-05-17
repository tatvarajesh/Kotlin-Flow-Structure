package com.example.demokotlinflow.domain.logout

import com.example.demokotlinflow.data.logout.remote.request.LogoutRequest
import com.example.demokotlinflow.domain.logout.entity.LogoutEntity
import retrofit2.http.Body

interface LogoutRepository {
    suspend fun callLogoutCustomer(@Body logoutRequest: LogoutRequest?): LogoutEntity
}