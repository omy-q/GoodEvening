package com.example.goodevening.domainmodel.room.genres

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenresDAO {
    @Query("SELECT * FROM GenresEntity")
    fun all(): List<GenresEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entities: List<GenresEntity>)
}