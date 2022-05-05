package com.example.demokotlinflow.demo2.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseApi {
    @GET("api/users")
    suspend fun getAllUser(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ClsUserResponse
}