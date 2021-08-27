package com.example.goodevening.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(RecentEntity::class), version = 1, exportSchema = false)
abstract class RecentDataBase : RoomDatabase(){
    abstract fun popularDao(): RecentDAO
}
