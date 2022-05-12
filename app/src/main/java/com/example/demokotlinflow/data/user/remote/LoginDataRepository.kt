package com.example.demokotlinflow.data.user.remote

import com.example.demokotlinflow.data.base.BaseLoginService
import com.example.demokotlinflow.data.user.remote.request.LoginRequest
import com.example.demokotlinflow.domain.user.LoginRepository
import com.example.demokotlinflow.domain.user.entity.LoginEntity
import java.util.stream.BaseStream

class LoginDataRepository:LoginRepository {
    override suspend fun callLoginCustomer(loginRequest: LoginRequest?): LoginEntity {
        return BaseLoginService().getBaseApi()?.callLoginCustomer(loginRequest).let {
            mapLoginData(it)
        }
    }
}