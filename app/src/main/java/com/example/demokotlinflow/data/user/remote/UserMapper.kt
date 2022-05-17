package com.example.demokotlinflow.data.user.remote

import com.example.demokotlinflow.data.user.remote.response.LoginResponse
import com.example.demokotlinflow.domain.user.entity.LoginEntity

fun mapLoginData(loginResponse: LoginResponse?):LoginEntity{
    return LoginEntity(loginResponse?.email,loginResponse?.mobile_number,loginResponse?.name,loginResponse?.status)
}