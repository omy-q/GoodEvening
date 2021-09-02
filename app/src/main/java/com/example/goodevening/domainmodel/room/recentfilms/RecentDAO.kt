package com.example.goodevening.domainmodel.room.recentfilms

import androidx.room.*

@Dao
interface RecentDAO {
    @Query("SELECT * FROM RecentEntity")
    fun all(): List<RecentEntity>

    @Query("SELECT * FROM RecentEntity ORDER BY date")
    fun allOrderByDate(): List<RecentEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: RecentEntity)

    @Update
    fun update(entity: RecentEntity)

    @Delete
    fun delete(entity: RecentEntity)
}
