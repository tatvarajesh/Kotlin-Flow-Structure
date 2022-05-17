package com.example.demokotlinflow.presentation.user.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.data.user.remote.request.LoginRequest
import com.example.demokotlinflow.domain.user.entity.LoginEntity
import com.example.demokotlinflow.domain.user.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
open class LoginViewModel @Inject constructor(
    private var loginUseCase: LoginUseCase,
    @ApplicationContext val context: Context
) : ViewModel() {

    //    //way 1
    private val _loginStateFlow = MutableStateFlow(LoginEntity())
    val loginStateFlow = _loginStateFlow as StateFlow<LoginEntity>

    private val _loadingStateFlow = MutableStateFlow(false)
    val loadingStateFlow = _loadingStateFlow as StateFlow<Boolean>

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow = _errorStateFlow as StateFlow<String>

    fun callLoginApi(mobileNumber: String, password: String) {
        loginUseCase(
            LoginRequest(
                "dQxz-LISRX22j_K9H8aJy_:APA91bFAP5O7UjxrCiS4xLLLiq6EZ1K1UKsogV1Yw2btqTABo6SqeGiK_Qrigrqmpwig1zQUgPHJ7DjaHVTU0qB-OTITFV28AkCueqSdJC0itc9Qi7qAOavi1tlxpYQEfYDklPL_oDg9",
                mobileNumber,
                password
            )
        ).onEach {
            when (it) {
                is Resource.Loading -> {
                    _loadingStateFlow.value = true
                }
                is Resource.Error -> {
                    _loadingStateFlow.value = false
                    _errorStateFlow.value = it.message.toString()
                }
                is Resource.Success -> {
                    _loadingStateFlow.value = false
                    _loginStateFlow.value = it.data as LoginEntity
                }
            }
        }.launchIn(viewModelScope)
    }
}