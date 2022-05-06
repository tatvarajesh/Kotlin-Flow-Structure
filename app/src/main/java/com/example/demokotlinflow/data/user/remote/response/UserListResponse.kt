package com.example.demokotlinflow.data.user.remote.response

data class UserListResponse(
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