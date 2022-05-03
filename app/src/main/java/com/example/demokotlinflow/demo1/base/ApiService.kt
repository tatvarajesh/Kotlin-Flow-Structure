package com.example.demokotlinflow.demo1.base

import com.example.demokotlinflow.demo1.CommentModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel

}