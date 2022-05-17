package com.example.demokotlinflow.data.logout.remote.request

data class LogoutRequest(
    var device_token: String?,
    var mobile_number: String?,
    var password: String?
)