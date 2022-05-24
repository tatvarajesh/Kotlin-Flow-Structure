package com.example.demokotlinflow.data.base.remote

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

    val networkService: T
        get() {
            var service = _networkService
            return if (service == null) {
                service = initNetworkService()
                _networkService = service
                return service
            } else service
        }

    private fun initNetworkService(): T {
        return handleRetrofitBuilder(Retrofit.Builder().baseUrl(BuildConfig.VOLTUP_BASE_URL))
            .addConverterFactory(GsonConverterFactory.create(handleGson(GsonBuilder()).create()))
            .client(getOkHttpClient())
            .build()
            .create(baseClass)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClient = handleOkHttpBuilder(OkHttpClient.Builder())

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        }
        return okHttpClient.build()
    }

    @CallSuper
    protected open fun handleRetrofitBuilder(builder: Retrofit.Builder) = builder

    @CallSuper
    protected open fun handleOkHttpBuilder(builder: OkHttpClient.Builder) = builder

    @CallSuper
    protected open fun handleGson(builder: GsonBuilder) = builder

}