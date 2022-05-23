package com.example.demokotlinflow.di

import com.example.demokotlinflow.data.addon.remote.AddOnDataRepository
import com.example.demokotlinflow.data.login.remote.LoginDataRepository
import com.example.demokotlinflow.data.logout.remote.LogoutDataRepository
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import com.example.demokotlinflow.domain.login.LoginRepository
import com.example.demokotlinflow.domain.login.usecase.LoginUseCase
import com.example.demokotlinflow.domain.logout.LogoutRepository
import com.example.demokotlinflow.domain.logout.usecase.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginDataRepository()
    }

    @Provides
    fun getLoginUseCase(): LoginUseCase {
        return LoginUseCase(provideLoginRepository())
    }

    @Provides
    fun provideLogoutRepository(): LogoutRepository {
        return LogoutDataRepository()
    }

    @Provides
    fun getLogoutUseCase(): LogoutUseCase {
        return LogoutUseCase(provideLogoutRepository())
    }

    @Provides
    fun provideAddOnRepository(): AddOnRepository {
        return AddOnDataRepository()
    }

    @Provides
    fun getAddOnUseCase(): AddOnUseCase {
        return AddOnUseCase(provideAddOnRepository())
    }
}