package com.example.demokotlinflow.presentation.user.demo2.base

data class ClsUserResponse(
    val `data`: Data?=null,
    val message: Any?=null,
    val status: Boolean?=null
) {
    data class Data(
        val has_more: Boolean,
        val users: List<User>
    ) {
        data class User(
            val image: String,
            val items: ArrayList<String>,
            val name: String
        )
    }
}