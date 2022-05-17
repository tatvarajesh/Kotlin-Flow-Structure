package com.example.demokotlinflow.data.user.remote

import com.example.demokotlinflow.data.base.BaseApi
import com.example.demokotlinflow.data.base.CommonService
import com.example.demokotlinflow.data.user.remote.request.LoginRequest
import com.example.demokotlinflow.domain.user.LoginRepository
import com.example.demokotlinflow.domain.user.entity.LoginEntity
import javax.inject.Inject

class LoginDataRepository @Inject constructor(var baseApi: BaseApi): CommonService<BaseApi>(),LoginRepository{

    override val baseClass: Class<BaseApi>
        get() = BaseApi::class.java

    override suspend fun callLoginCustomer(loginRequest: LoginRequest?): LoginEntity {
        return mapLoginData(baseApi.callLoginCustomer(loginRequest))
    }
}