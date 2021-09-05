package com.example.goodevening.domainmodel.room.favoritefilms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM FavoriteEntity")
    fun all(): List<FavoriteEntity>

    @Query("SELECT * FROM FavoriteEntity ORDER BY date")
    fun allOrderByDate(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavoriteEntity)
}