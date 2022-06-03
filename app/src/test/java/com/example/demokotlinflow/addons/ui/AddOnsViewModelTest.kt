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
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import com.example.demokotlinflow.presentation.addon.viewmodel.AddOnViewModel
import junit.framework.Assert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
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
    private lateinit var addOnUseCase: AddOnUseCase
    private lateinit var fakeRepository: FakeNoteRepository

    @Mock
    private lateinit var apiHelper: AddOnApiService

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

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
            doReturn(flowOf(emptyList<AddOnEntity>()))
                .`when`(apiHelper).callCustomerAddOn("756", 20, 1)
            addOnViewModel.callAddOnApi()
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}