package com.example.demokotlinflow.data.login.remote

import com.example.demokotlinflow.data.login.remote.response.LoginResponse
import com.example.demokotlinflow.domain.login.entity.LoginEntity

fun mapLoginData(loginResponse: LoginResponse?): LoginEntity {
    return LoginEntity(
        loginResponse?.email,
        loginResponse?.mobile_number,
        loginResponse?.name,
        loginResponse?.status
    )
}