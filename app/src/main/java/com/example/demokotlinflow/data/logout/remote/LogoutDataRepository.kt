package com.example.demokotlinflow.data.logout.remote

import com.example.demokotlinflow.data.base.remote.CommonService
import com.example.demokotlinflow.data.logout.remote.request.LogoutRequest
import com.example.demokotlinflow.domain.logout.LogoutRepository
import com.example.demokotlinflow.domain.logout.entity.LogoutEntity

class LogoutDataRepository : CommonService<LogoutApiService>(), LogoutRepository {

    override val baseClass: Class<LogoutApiService>
        get() = LogoutApiService::class.java

    override suspend fun callLogoutCustomer(logoutRequest: LogoutRequest?): LogoutEntity {
        return mapLogoutData(networkService.callLogoutCustomer(logoutRequest))
    }
}