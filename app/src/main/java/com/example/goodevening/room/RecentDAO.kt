package com.example.goodevening.room
import androidx.room.*

@Dao
interface RecentDAO {
    @Query("SELECT * FROM RecentEntity")
    fun all(): List<RecentEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: RecentEntity)

    @Update
    fun update(entity: RecentEntity)

    @Delete
    fun delete(entity: RecentEntity)
}
