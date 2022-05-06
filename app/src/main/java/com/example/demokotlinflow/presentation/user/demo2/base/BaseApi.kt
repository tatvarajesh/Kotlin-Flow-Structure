package com.example.demokotlinflow.presentation.user.demo2.base

import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET("api/users")
    suspend fun getAllUser(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ClsUserResponse
}