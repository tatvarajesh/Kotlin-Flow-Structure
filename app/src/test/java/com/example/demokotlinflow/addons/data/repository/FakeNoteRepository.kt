package com.example.demokotlinflow.addons.data.repository

import com.example.demokotlinflow.domain.addon.AddOnRepository
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity

class FakeNoteRepository : AddOnRepository {

    private val notes = mutableListOf<AddOnEntity>()

    override suspend fun callCustomerAddOn(
        customerId: String,
        per: Int,
        page: Int
    ): List<AddOnEntity>? {
        return notes
    }

    override suspend fun insertAddOns(userList: List<AddOnEntity>) {
        notes.addAll(userList)
    }

    override fun getAllAddOnsFromDb(): List<AddOnEntity> {
        return notes
    }
}