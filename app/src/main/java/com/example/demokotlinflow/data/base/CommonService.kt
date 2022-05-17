package com.example.demokotlinflow.data.base

import androidx.annotation.CallSuper
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


abstract class CommonService<T>() : BaseServices<T>() {

    @Inject
    lateinit var networkErrorInterceptor: NetworkErrorInterceptor

    @CallSuper
    override fun handleOkHttpBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return super.handleOkHttpBuilder(builder)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(networkErrorInterceptor)
    }
}