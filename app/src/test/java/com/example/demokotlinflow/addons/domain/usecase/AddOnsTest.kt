package com.example.demokotlinflow.addons.domain.usecase

import com.example.demokotlinflow.addons.data.repository.FakeNoteRepository
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import com.example.demokotlinflow.domain.addon.usecase.AddOnUseCase
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddOnsTest {

    private lateinit var getNotes: AddOnUseCase
    private lateinit var fakeRepository: FakeNoteRepository
    private val realData = mutableListOf<AddOnEntity>()

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        getNotes = AddOnUseCase(fakeRepository)

        ('a'..'z').forEachIndexed { index, c ->
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
        runBlocking {
            fakeRepository.insertAddOns(realData)
        }
    }

    @Test
    fun `Verify Api and Local size are same`() = runBlocking {
        val localData = fakeRepository.getAllAddOnsFromDb()
        assertTrue(
            realData.size == localData.size && realData.containsAll(localData) && localData.containsAll(
                realData
            )
        )
    }

    @Test
    fun `Verify Api and Local size are not same after deleting record`() = runBlocking {
        val localData = fakeRepository.getAllAddOnsFromDb()
        realData.removeAt(5)
        assertFalse(
            realData.size == localData.size && realData.containsAll(localData) && localData.containsAll(
                realData
            )
        )
    }
}