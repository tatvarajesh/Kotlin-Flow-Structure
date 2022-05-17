package com.example.demokotlinflow.data.logout.remote

import com.example.demokotlinflow.data.logout.remote.response.LogoutResponse
import com.example.demokotlinflow.domain.logout.entity.LogoutEntity

fun mapLogoutData(logoutResponse: LogoutResponse?): LogoutEntity {
    return LogoutEntity(
        logoutResponse?.email,
        logoutResponse?.mobile_number,
        logoutResponse?.name,
        logoutResponse?.status
    )
}