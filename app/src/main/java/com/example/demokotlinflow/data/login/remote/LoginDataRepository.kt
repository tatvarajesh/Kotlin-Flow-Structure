package com.example.demokotlinflow.data.login.remote

import com.example.demokotlinflow.data.base.CommonService
import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.login.LoginRepository
import com.example.demokotlinflow.domain.login.entity.LoginEntity

class LoginDataRepository : CommonService<LoginApiService>(), LoginRepository {

    override val baseClass: Class<LoginApiService>
        get() = LoginApiService::class.java

    override suspend fun callLoginCustomer(loginRequest: LoginRequest?): LoginEntity {
        return mapLoginData(networkService.callLoginCustomer(loginRequest))
    }
}