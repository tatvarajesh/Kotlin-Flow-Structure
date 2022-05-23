package com.example.demokotlinflow.data.addon.remote

import com.example.demokotlinflow.data.base.CommonService
import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.login.LoginRepository
import com.example.demokotlinflow.domain.login.entity.LoginEntity

class AddOnDataRepository : CommonService<AddOnApiService>(), AddOnRepository {

    override val baseClass: Class<AddOnApiService>
        get() = AddOnApiService::class.java

    override suspend fun callCustomerAddOn(customerId: String, per: Int, page: Int): AddOnEntity {
        return mapAddOnData(networkService.callCustomerAddOn(customerId, per, page))
    }
}