package com.example.demokotlinflow.domain.addon.usecase

import com.example.demokotlinflow.data.base.ApiHttpException
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.data.login.remote.request.LoginRequest
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.login.LoginRepository
import com.example.demokotlinflow.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddOnUseCase @Inject constructor(private val addOnRepository: AddOnRepository) {
    operator fun invoke(
        customerId: String,
        per: Int,
        page: Int
    ): Flow<Resource<AddOnEntity>> = flow {
        try {
            emit(Resource.Loading())
            val addOnData = addOnRepository.callCustomerAddOn(customerId, per, page)
            emit(Resource.Success(addOnData))
        } catch (e: ApiHttpException) {
            emit(Resource.Error(e.getErrorMessage()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: Exception) {
        }
    }
}