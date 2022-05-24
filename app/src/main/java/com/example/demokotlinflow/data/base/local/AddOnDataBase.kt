package com.example.demokotlinflow.data.base.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demokotlinflow.data.addon.local.AddOnDao
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity


@Database(entities = [AddOnEntity::class], version = 1, exportSchema = false)
abstract class AddOnDataBase : RoomDatabase() {
    abstract fun addOnDao(): AddOnDao
}