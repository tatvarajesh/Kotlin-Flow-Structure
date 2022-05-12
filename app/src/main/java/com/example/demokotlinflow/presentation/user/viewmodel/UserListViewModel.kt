package com.example.demokotlinflow.presentation.user.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.domain.user.entity.UserListEntity
import com.example.demokotlinflow.domain.user.usecase.UserListUseCase
import com.example.demokotlinflow.util.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
open class UserListViewModel @Inject constructor(
    private var userListUseCase: UserListUseCase,
    @ApplicationContext val context: Context
) : ViewModel() {
    private val users = MutableLiveData<UserListEntity>()

    //    //way 1
    private val _userListStateFlow = MutableStateFlow(UserListEntity())
    val userListStateFlow = _userListStateFlow as StateFlow<UserListEntity>

    private val _loadingStateFlow = MutableStateFlow(false)
    val loadingStateFlow = _loadingStateFlow as StateFlow<Boolean>

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow = _errorStateFlow as StateFlow<String>

    fun callUserListApi(offset: Int) {
        if (isNetworkAvailable(context)) {
            userListUseCase(offset, 10).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _loadingStateFlow.value = true
                    }
                    is Resource.Error -> {
                        _loadingStateFlow.value = false
                        _errorStateFlow.value = it.toString()
                    }
                    is Resource.Success -> {
                        _loadingStateFlow.value = false
                        _userListStateFlow.value = it.data as UserListEntity
                        users.postValue(it.data as UserListEntity)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            _errorStateFlow.value = "No network available"
        }
    }


    open fun getUsers(): LiveData<UserListEntity> {
        return users
    }
}