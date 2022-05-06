package com.example.demokotlinflow.domain.user

import com.example.demokotlinflow.domain.user.entity.UserListEntity

interface UserListRepository {
    suspend fun getUserList(offset: Int,limit: Int):UserListEntity
}