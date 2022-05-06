package com.example.demokotlinflow.data.user.remote

import com.example.demokotlinflow.data.user.remote.response.UserListResponse
import com.example.demokotlinflow.domain.user.entity.UserListEntity

fun mapUserList(userListResponse: UserListResponse?):UserListEntity{
    return UserListEntity(userListResponse?.data,userListResponse?.message,userListResponse?.status)
}