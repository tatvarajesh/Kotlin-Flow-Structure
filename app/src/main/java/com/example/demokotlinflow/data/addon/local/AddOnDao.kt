package com.example.demokotlinflow.data.addon.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demokotlinflow.domain.addon.entity.AddOnEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddOnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddOns(userList: List<AddOnEntity>)

    @Query("SELECT * FROM table_addon")
    fun getAllAddOnsFromDb(): Flow<List<AddOnEntity>>

}