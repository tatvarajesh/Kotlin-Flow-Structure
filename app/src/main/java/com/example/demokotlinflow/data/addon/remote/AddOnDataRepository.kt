package com.example.demokotlinflow.data.addon.remote

import androidx.lifecycle.LiveData
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.base.remote.CommonService
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddOnDataRepository @Inject constructor(var addOnDao: AddOnDao) :
    CommonService<AddOnApiService>(), AddOnRepository {

    override val baseClass: Class<AddOnApiService>
        get() = AddOnApiService::class.java

    override suspend fun callCustomerAddOn(
        customerId: String,
        per: Int,
        page: Int
    ): List<AddOnEntity>? {
        return networkService.callCustomerAddOn(customerId, per, page).map()
    }

    override suspend fun insertAddOns(userList: List<AddOnEntity>) {
        addOnDao.insertAddOns(userList)
    }

    override fun getAllAddOnsFromDb(): List<AddOnEntity> {
        return addOnDao.getAllAddOnsFromDb()
    }

}