package com.example.goodevening.domainmodel.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteDAO
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteEntity
import com.example.goodevening.domainmodel.room.genres.GenresDAO
import com.example.goodevening.domainmodel.room.genres.GenresEntity
import com.example.goodevening.domainmodel.room.popularfilms.PopularDAO
import com.example.goodevening.domainmodel.room.popularfilms.PopularEntity
import com.example.goodevening.domainmodel.room.recentfilms.RecentDAO
import com.example.goodevening.domainmodel.room.recentfilms.RecentEntity
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedDAO
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedEntity
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchDAO
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchEntity
import com.example.goodevening.domainmodel.utils.DateConverter


@Database(entities = [
    GenresEntity::class,
    RecentEntity::class,
    FavoriteEntity::class,
    WatchedEntity::class,
    WillWatchEntity::class,
    PopularEntity::class],
    version = 1, exportSchema = false
)
//@TypeConverters(DateConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun genresDao(): GenresDAO
    abstract fun recentDao(): RecentDAO
    abstract fun favoriteDao(): FavoriteDAO
    abstract fun watchedDao(): WatchedDAO
    abstract fun willwatchDao(): WillWatchDAO
    abstract fun popularDao(): PopularDAO
}
