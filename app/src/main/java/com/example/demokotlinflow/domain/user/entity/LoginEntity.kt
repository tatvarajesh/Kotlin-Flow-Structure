package com.example.demokotlinflow.domain.user.entity

data class LoginEntity(var email: String?=null,
                       var mobile_number: Any?=null,
                       var name: String?=null,
                       var status: String?=null)