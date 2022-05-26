package com.example.demokotlinflow.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonBuilderUtil{
    private var gson: Gson? = null

    fun getGsonParser(): Gson? {
        if (null == gson) {
            val builder = GsonBuilder()
            gson = builder.create()
        }
        return gson
    }
}