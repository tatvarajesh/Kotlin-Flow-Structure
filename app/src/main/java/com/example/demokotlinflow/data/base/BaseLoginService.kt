package com.example.demokotlinflow.data.base

import com.example.demokotlinflow.BuildConfig
import com.example.demokotlinflow.data.user.BaseApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BaseLoginService() {
    private var retrofitBaseApi: Retrofit? = null

    fun getBaseApi(): BaseApi? {
        return createRetrofitBase().create(BaseApi::class.java)
    }

    private fun createRetrofitBase(): Retrofit {
        if (retrofitBaseApi == null) {
            retrofitBaseApi = Retrofit.Builder()
                .baseUrl("https://preprod-voltup-api.voltup.in/")
                .addConverterFactory(
                    GsonConverterFactory.create(
//                        GsonBuilder()
//                            .excludeFieldsWithoutExposeAnnotation().create()
                    )
                )
                .client(getOkHttpClient())
                .build()
        }
        return retrofitBaseApi as Retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .header("Authorization", "Bearer ")
                .header("requestFrom", "mobile")
                .header("deviceId", "de55dac5bf9596ee")
                .header("roleId", "7")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}