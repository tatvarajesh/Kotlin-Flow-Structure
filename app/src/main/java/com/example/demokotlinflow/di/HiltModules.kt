package com.example.demokotlinflow.di

import com.example.demokotlinflow.data.user.remote.UserListDataRepository
import com.example.demokotlinflow.domain.user.UserListRepository
import com.example.demokotlinflow.domain.user.usecase.UserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {
    @Provides
    fun provideUserListRepository(): UserListRepository {
        return UserListDataRepository()
    }

    @Provides
    fun getUserListUseCase(): UserListUseCase {
        return UserListUseCase(provideUserListRepository())
    }
}