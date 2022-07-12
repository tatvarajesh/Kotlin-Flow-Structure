package com.example.demokotlinflow.addons.ui

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.demokotlinflow.TestCoroutineRule
import com.example.demokotlinflow.addons.data.repository.FakeNoteRepository
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.data.addon.remote.AddOnApiService
import com.example.demokotlinflow.data.base.local.AddOnDataBase
import com.example.demokotlinflow.data.base.remote.Resource
import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import com.example.demokotlinflow.presentation.addon.viewmodel.AddOnViewModel
import io.mockk.mockk
import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Q])
class AddOnsViewModelTest {

    private lateinit var addOnDao: AddOnDao
    private lateinit var db: AddOnDataBase
    private lateinit var addOnViewModel: AddOnViewModel
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val realData = mutableListOf<AddOnEntity>()
    private var addOnUseCase: AddOnUseCase = mockk()
    private lateinit var fakeRepository: FakeNoteRepository
    private var addOnRepository: AddOnRepository = mockk()

    @Mock
    private lateinit var apiHelper: AddOnApiService

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Flow<Resource<List<AddOnEntity>>>

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            context, AddOnDataBase::class.java
        ).allowMainThreadQueries().build()
        addOnDao = db.addOnDao()
        fakeRepository = FakeNoteRepository()
        addOnUseCase = AddOnUseCase(fakeRepository)

        addOnViewModel = AddOnViewModel(addOnUseCase, addOnDao)

        ('a'..'e').forEachIndexed { index, c ->
            realData.add(
                AddOnEntity(
                    addon_name = c.toString(),
                    addon_price = (index * 2).toString(),
                    discount_amount = index,
                    addon_usage_status = "N/A",
                    id = index
                )
            )
        }
        realData.shuffle()
    }

    @Test
    fun `Verify data stored in db`() = runBlocking {
        addOnDao.insertAll(realData)
        val localData = addOnDao.getAllAddOnsFromDb()
        Assert.assertTrue(localData.isNotEmpty())
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            addOnViewModel.callAddOnApi()
        }
    }

    @Test
    fun api_return_emptyList() = runTest {

        val dispatcher = UnconfinedTestDispatcher(testScheduler)

        var list: List<AddOnEntity> = arrayListOf()
        val job = launch(dispatcher) { list = addOnViewModel.addOnEntityStateFlow.value }


        runCurrent()

        assertEquals(0, list.size)
        job.cancel()
    }

//    @Test
//    fun api_return_List()= runTest {
//        var size = 0
//        val dispatcher = UnconfinedTestDispatcher(testScheduler)
//
//        var list: String =""
//        val job = launch(dispatcher) { addOnViewModel._addOnStateFlow.collect{
//            list = it
//        } }
//
//
//        runCurrent()
//
//        assertEquals("1", list)
//        job.cancel()
//    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}