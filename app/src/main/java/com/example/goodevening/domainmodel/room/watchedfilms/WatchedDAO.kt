package com.example.goodevening.domainmodel.room.watchedfilms

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface WatchedDAO {
    @Query("SELECT * FROM WatchedEntity")
    fun all(): List<WatchedEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WatchedEntity)
}