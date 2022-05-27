package com.example.demokotlinflow


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.addon.remote.AddOnApiService
import com.example.demokotlinflow.data.base.remote.Resource
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    private lateinit var addOnRepository: AddOnRepository

    @Mock
    private lateinit var addOnUseCase: AddOnUseCase

    @Mock
    private lateinit var addOnDao: AddOnDao

    @Mock
    lateinit var context: Context

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        context = Mockito.mock(Context::class.java)
        testCoroutineRule.runBlockingTest {
//            doReturn(flowOf(AddOnEntity()))
//                .`when`(addOnRepository).callCustomerAddOn("756",20,1)

//            addOnDao.insert(AddOnEntity(0,1,"hello","11",5,"55"))

//            addOnUseCase.invoke("455",10,1)
        }
    }

}