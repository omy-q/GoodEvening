package com.example.goodevening.domainmodel.room.popularfilms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularDAO {
    @Query("SELECT * FROM PopularEntity")
    fun all(): List<PopularEntity>

    @Query("SELECT * FROM PopularEntity ORDER BY date")
    fun allOrderByDate(): List<PopularEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: List<PopularEntity>)
}