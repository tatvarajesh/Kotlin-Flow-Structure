package com.example.demokotlinflow.data.addon.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demokotlinflow.data.base.local.BaseDao
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AddOnDao: BaseDao<AddOnEntity> {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAddOns(userList: List<AddOnEntity>)


    @Query("SELECT * FROM table_addon")
    abstract fun getAllAddOnsFromDb(): List<AddOnEntity>
}