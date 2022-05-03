package com.example.demokotlinflow.demo2.ui

import androidx.lifecycle.viewModelScope
import com.example.demokotlinflow.demo2.base.BaseApiState
import com.example.demokotlinflow.demo2.base.BaseService
import com.example.demokotlinflow.demo2.base.ClsUserResponse
import com.example.demokotlinflow.demo2.base.Status
import com.example.demokotlinflow.demo2.repository.UserListRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel : BaseObservableViewModel() {

    private val userListRepository = BaseService().getBaseApi()?.let { UserListRepository(it) }

    //way 1
    private val _userListStateFlow = MutableStateFlow(ClsUserResponse())
    val userListStateFlow = _userListStateFlow as StateFlow<ClsUserResponse>

    private val _loadingStateFlow = MutableStateFlow(true)
    val loadingStateFlow = _loadingStateFlow as StateFlow<Boolean>

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow = _errorStateFlow as StateFlow<String>

    //way 2 (using baseState)
    val userStateFlow = MutableStateFlow(
        BaseApiState(
            Status.LOADING,
            ClsUserResponse(), ""
        )
    )

    //way 3
    private val _userListSharedFlow = MutableSharedFlow<ClsUserResponse>()
    val userListSharedFlow = _userListSharedFlow as SharedFlow<ClsUserResponse>
    private val _loadingSharedFlow = MutableSharedFlow<Boolean>()
    val loadingSharedFlow = _loadingSharedFlow as SharedFlow<Boolean>
    private val _errorSharedFlow = MutableSharedFlow<String>()
    val errorSharedFlow = _errorSharedFlow as SharedFlow<String>


    fun callUserListApi() {
        _loadingStateFlow.value = true//way 1

        userStateFlow.value = BaseApiState.loading()//way 2

        viewModelScope.launch {
            _loadingSharedFlow.emit(true)
        }//way 3

        viewModelScope.launch {
            userListRepository?.getUserList(0, 10)?.catch {
                _loadingStateFlow.value = false//way 1
                _errorStateFlow.value = it.localizedMessage.toString()//way 1

                userStateFlow.value = BaseApiState.error(it.localizedMessage.toString())//way 2

                _loadingSharedFlow.emit(true)//way 3
                _errorSharedFlow.emit(it.localizedMessage.toString())//way 3


            }?.collect {
                _loadingStateFlow.value = false//way 1
                _userListStateFlow.value = it//way 1

                userStateFlow.value = BaseApiState.success(it)//way 2

                _loadingSharedFlow.emit(true)//way 3
                _userListSharedFlow.emit(it)//way3

            }
        }
    }
}