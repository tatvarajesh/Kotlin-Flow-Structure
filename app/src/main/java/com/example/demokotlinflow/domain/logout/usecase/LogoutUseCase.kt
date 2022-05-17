package com.example.demokotlinflow.domain.logout.usecase

import com.example.demokotlinflow.data.base.ApiHttpException
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.data.logout.remote.request.LogoutRequest
import com.example.demokotlinflow.domain.logout.LogoutRepository
import com.example.demokotlinflow.domain.logout.entity.LogoutEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val logoutRepository: LogoutRepository) {
    operator fun invoke(
        logoutRequest: LogoutRequest
    ): Flow<Resource<LogoutEntity>> = flow {
        try {
            emit(Resource.Loading())
            val logoutData = logoutRepository.callLogoutCustomer(logoutRequest)
            emit(Resource.Success(logoutData))
        } catch (e: ApiHttpException) {
            emit(Resource.Error(e.getErrorMessage()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: Exception) {
        }
    }
}