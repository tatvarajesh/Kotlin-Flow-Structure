package com.example.demokotlinflow.data.base.local

import androidx.room.*


interface BaseDao<T> {
    
    @Insert
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<T>)
    
    @Update
    fun update(entity: T)
    
    @Update
    fun updateAll(entities: List<T>)
    
    @Delete
    fun delete(entity: T)

    @Delete
    fun deleteAll(entities: List<T>)


}