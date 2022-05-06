package com.example.demokotlinflow.data.user

import com.example.demokotlinflow.data.user.remote.response.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET("api/users")
    suspend fun getAllUser(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): UserListResponse
}