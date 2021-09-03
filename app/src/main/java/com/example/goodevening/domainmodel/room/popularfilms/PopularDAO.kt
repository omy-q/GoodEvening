package com.example.goodevening.domainmodel.room.popularfilms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularDAO {
    @Query("SELECT * FROM PopularEntity")
    fun all(): List<PopularEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: List<PopularEntity>)
}