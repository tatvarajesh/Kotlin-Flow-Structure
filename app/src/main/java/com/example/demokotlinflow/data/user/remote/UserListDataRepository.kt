package com.example.demokotlinflow.data.user.remote

import com.example.demokotlinflow.data.base.BaseService
import com.example.demokotlinflow.domain.user.UserListRepository
import com.example.demokotlinflow.domain.user.entity.UserListEntity

class UserListDataRepository:UserListRepository {
    override suspend fun getUserList(offset: Int, limit: Int): UserListEntity {
        return BaseService().getBaseApi()?.getAllUser(offset, limit).let {
            mapUserList(it)
        }
    }
}