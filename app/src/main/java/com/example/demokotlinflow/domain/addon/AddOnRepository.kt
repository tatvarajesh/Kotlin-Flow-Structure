package com.example.demokotlinflow.domain.addon

import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.login.entity.LoginEntity
import retrofit2.http.Body
import retrofit2.http.Query

interface AddOnRepository {
    suspend fun callCustomerAddOn(customerId: String,
                                  per: Int,
                                  page: Int): AddOnEntity
}