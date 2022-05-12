package com.example.demokotlinflow.data.user.remote.request

data class LoginRequest(
    var device_token: String?,
    var mobile_number: String?,
    var password: String?
)