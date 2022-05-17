package com.example.demokotlinflow.domain.login

import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.login.entity.LoginEntity
import retrofit2.http.Body

interface LoginRepository {
    suspend fun callLoginCustomer(@Body loginRequest: LoginRequest?): LoginEntity
}