package com.example.goodevening.domainmodel.room.willwatchfilms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WillWatchDAO {
    @Query("SELECT * FROM WillWatchEntity")
    fun all(): List<WillWatchEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WillWatchEntity)
}