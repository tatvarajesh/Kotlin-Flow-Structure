package com.example.demokotlinflow.data.base.remote

import androidx.annotation.CallSuper
import com.example.demokotlinflow.MyApp
import com.example.demokotlinflow.R
import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


abstract class CommonService<T>() : BaseServices<T>() {

    @CallSuper
    override fun handleOkHttpBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return super.handleOkHttpBuilder(builder)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(
                NetworkErrorInterceptor(
                    MyApp.instance.resources.getString(R.string.no_internet_connection),
                    Gson()
                )
            )
    }
}