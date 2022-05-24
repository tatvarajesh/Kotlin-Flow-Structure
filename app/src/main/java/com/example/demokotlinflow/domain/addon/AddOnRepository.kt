package com.example.demokotlinflow.domain.addon

import androidx.room.Query
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import kotlinx.coroutines.flow.Flow

interface AddOnRepository {
    suspend fun callCustomerAddOn(customerId: String,
                                  per: Int,
                                  page: Int): List<AddOnEntity>?

//    suspend fun insertAddOns(userList: List<AddOnEntity>)
//    fun getAllAddOnsFromDb(): List<AddOnEntity>
}