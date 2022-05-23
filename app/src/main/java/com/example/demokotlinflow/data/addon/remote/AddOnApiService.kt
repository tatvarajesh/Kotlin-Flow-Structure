package com.example.demokotlinflow.data.addon.remote

import com.example.demokotlinflow.data.addon.remote.response.AddOnResponse
import com.example.demokotlinflow.data.login.remote.response.LoginResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface AddOnApiService {
    @GET("api/v2/customer_addons")
    suspend fun callCustomerAddOn(
        @Query("customer_id") customerId: String,
        @Query("per") per: Int,
        @Query("page") page: Int
    ): AddOnResponse
}