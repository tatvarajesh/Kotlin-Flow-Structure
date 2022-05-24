package com.example.demokotlinflow.data.addon.remote

import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.base.remote.CommonService
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddOnDataRepository: CommonService<AddOnApiService>(), AddOnRepository {

    override val baseClass: Class<AddOnApiService>
        get() = AddOnApiService::class.java

    override suspend fun callCustomerAddOn(customerId: String, per: Int, page: Int): List<AddOnEntity>? {
        return networkService.callCustomerAddOn(customerId, per, page).map()
    }

}