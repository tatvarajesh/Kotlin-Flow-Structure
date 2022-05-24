package com.example.demokotlinflow.di

import android.content.Context
import androidx.room.Room
import com.example.demokotlinflow.MyApp
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.addon.remote.AddOnDataRepository
import com.example.demokotlinflow.data.base.local.AddOnDataBase
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
//
    //db
    @Provides
    @Singleton
    fun providesAddOnDatabase(@ApplicationContext context: Context): AddOnDataBase
            = Room.databaseBuilder(context,AddOnDataBase::class.java,"AddOnDataBase").build()

    @Provides
    fun providesAddOnDao(addOnDataBase: AddOnDataBase): AddOnDao = addOnDataBase.addOnDao()

}