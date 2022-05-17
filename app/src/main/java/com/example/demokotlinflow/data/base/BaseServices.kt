package com.example.demokotlinflow.data.base

import androidx.annotation.CallSuper
import com.example.demokotlinflow.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class BaseServices<T> {
    private var _networkService: T? = null

    protected abstract val baseClass: Class<T>

    @CallSuper
    protected open fun handleRetrofitBuilder(builder: Retrofit.Builder) = builder

    @CallSuper
    protected open fun handleOkHttpBuilder(builder: OkHttpClient.Builder) = builder

    @CallSuper
    protected open fun handleGson(builder: GsonBuilder) = builder

}