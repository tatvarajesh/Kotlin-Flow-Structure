package com.example.demokotlinflow.domain.user

import com.example.demokotlinflow.data.user.remote.request.LoginRequest
import com.example.demokotlinflow.domain.user.entity.LoginEntity
import retrofit2.http.Body

interface LoginRepository {
    suspend fun callLoginCustomer(@Body loginRequest: LoginRequest?): LoginEntity
}