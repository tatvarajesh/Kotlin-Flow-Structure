package com.example.demokotlinflow.presentation.user.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlinflow.data.base.Resource
import com.example.demokotlinflow.domain.user.entity.UserListEntity
import com.example.demokotlinflow.domain.user.usecase.UserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private var userListUseCase: UserListUseCase,
    @ApplicationContext val context: Context
) : ViewModel() {

    //    private val userListRepository = BaseService().getBaseApi()?.let { UserRepository(it) }
//
//    //way 1
    private val _userListStateFlow = MutableStateFlow(UserListEntity())
    val userListStateFlow = _userListStateFlow as StateFlow<UserListEntity>

    private val _loadingStateFlow = MutableStateFlow(true)
    val loadingStateFlow = _loadingStateFlow as StateFlow<Boolean>

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow = _errorStateFlow as StateFlow<String>
//
//    //way 2 (using baseState)
//    val userStateFlow = MutableStateFlow(
//        BaseApiState(
//            Status.LOADING,
//            UserListResponse(), ""
//        )
//    )
//
//    //way 3
//    private val _userListSharedFlow = MutableSharedFlow<UserListResponse>()
//    val userListSharedFlow = _userListSharedFlow as SharedFlow<UserListResponse>
//    private val _loadingSharedFlow = MutableSharedFlow<Boolean>()
//    val loadingSharedFlow = _loadingSharedFlow as SharedFlow<Boolean>
//    private val _errorSharedFlow = MutableSharedFlow<String>()
//    val errorSharedFlow = _errorSharedFlow as SharedFlow<String>
//

    var loadingLiveData = MutableLiveData(false)
    var errorLiveData = MutableLiveData("")
    var loginDataLiveData = MutableLiveData<UserListEntity>()

    fun callUserListApi() {
        _loadingStateFlow.value = true//way 1

        userListUseCase(0, 10).onEach {
            when (it) {
                is Resource.Loading -> {
                    loadingLiveData.postValue(true)
                }
                is Resource.Error -> {
                    _loadingStateFlow.value = false//way 1
                    _errorStateFlow.value = it.toString()//way 1
                }
                is Resource.Success -> {
                    _loadingStateFlow.value = false//way 1
                    _userListStateFlow.value = it.data as UserListEntity//way 1
                }
            }
        }.launchIn(viewModelScope)
    }
}