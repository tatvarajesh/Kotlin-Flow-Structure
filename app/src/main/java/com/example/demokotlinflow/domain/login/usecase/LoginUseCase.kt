package com.example.demokotlinflow.domain.login.usecase

import com.example.demokotlinflow.data.base.remote.ApiHttpException
import com.example.demokotlinflow.data.base.remote.Resource
import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.login.LoginRepository
import com.example.demokotlinflow.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    operator fun invoke(
        loginRequest: LoginRequest
    ): Flow<Resource<LoginEntity>> = flow {
        try {
            emit(Resource.Loading())
            val loginData = loginRepository.callLoginCustomer(loginRequest)
            emit(Resource.Success(loginData))
        } catch (e: ApiHttpException) {
            emit(Resource.Error(e.getErrorMessage()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: Exception) {
        }
    }
}