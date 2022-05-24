package com.example.demokotlinflow.presentation.addon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.base.remote.Resource
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddOnViewModel @Inject constructor(
    private var addOnUseCase: AddOnUseCase,
    private var addOnDao: AddOnDao
) : ViewModel() {

    private val _addOnEntityStateFlow = MutableStateFlow<List<AddOnEntity>>(arrayListOf())
    val addOnEntityStateFlow = _addOnEntityStateFlow as StateFlow<List<AddOnEntity>>

    val _addOnEntityDBListStateFlow = MutableStateFlow<List<AddOnEntity>>(arrayListOf())

    private val _loadingStateFlow = MutableStateFlow(false)
    val loadingStateFlow = _loadingStateFlow as StateFlow<Boolean>

    private val _errorStateFlow = MutableStateFlow("")
    val errorStateFlow = _errorStateFlow as StateFlow<String>

    fun callAddOnApi() {
        addOnUseCase("756",20,1).onEach {
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
                    _addOnEntityStateFlow.value = it.data as List<AddOnEntity>
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun insertAddOnsToDatabase(customerAddons: ArrayList<AddOnEntity>) {
        addOnDao.insertAddOns(customerAddons)
    }

    fun getAllFromDB(){
       addOnDao.getAllAddOnsFromDb().onEach {
           _addOnEntityDBListStateFlow.value = it
       }.launchIn(viewModelScope)
    }
}