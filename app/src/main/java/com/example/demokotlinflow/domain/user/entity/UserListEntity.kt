package com.example.demokotlinflow.domain.user.entity

import com.example.demokotlinflow.data.user.remote.response.UserListResponse


data class UserListEntity(
    val data: UserListResponse.Data? = null,
    val message: Any? = null,
    val status: Boolean? = null
) {
    data class Data(
        val users: List<User>
    ) {
        data class User(
            val image: String,
            val name: String
        )
    }
}