package com.example.demokotlinflow.di

import com.example.demokotlinflow.BuildConfig
import com.example.demokotlinflow.MyApp
import com.example.demokotlinflow.R
import com.example.demokotlinflow.data.base.BaseApi
import com.example.demokotlinflow.data.base.NetworkErrorInterceptor
import com.example.demokotlinflow.data.user.remote.LoginDataRepository
import com.example.demokotlinflow.domain.user.LoginRepository
import com.example.demokotlinflow.domain.user.usecase.LoginUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginDataRepository(provideApiUserInterface(OkHttpClient.Builder()))
    }

    @Provides
    fun getLoginUseCase(): LoginUseCase {
        return LoginUseCase(provideLoginRepository())
    }

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideApiUserInterface(retrofitClient: OkHttpClient.Builder): BaseApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.VOLTUP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient(retrofitClient))
            .build().create(BaseApi::class.java)

    private fun getOkHttpClient(build: OkHttpClient.Builder): OkHttpClient {
        return build.connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(provideNetworkErrorInterceptor(provideGson()))
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideNetworkErrorInterceptor(
        gson: Gson
    ): NetworkErrorInterceptor = NetworkErrorInterceptor(
        MyApp.instance.resources.getString(R.string.no_internet_connection),
        gson
    )
}