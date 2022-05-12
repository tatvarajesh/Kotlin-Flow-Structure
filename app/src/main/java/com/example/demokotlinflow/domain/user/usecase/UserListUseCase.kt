package com.example.demokotlinflow.domain.user.usecase

import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.domain.user.UserListRepository
import com.example.demokotlinflow.domain.user.entity.UserListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class UserListUseCase @Inject constructor(private val userListRepository: UserListRepository) {
    operator fun invoke(offset:Int,limit:Int): Flow<Resource<UserListEntity>> = flow {
        try {
            emit(Resource.Loading())
            val loginData = userListRepository.getUserList(offset, limit)
            emit(Resource.Success(loginData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: Exception) {
        }
    }
}