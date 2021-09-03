package com.example.goodevening.app

import android.app.Application
import androidx.room.Room
import com.example.goodevening.domainmodel.room.DataBase
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteDAO
import com.example.goodevening.domainmodel.room.genres.GenresDAO
import com.example.goodevening.domainmodel.room.popularfilms.PopularDAO
import com.example.goodevening.domainmodel.room.recentfilms.RecentDAO
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedDAO
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchDAO

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: DataBase? = null
        private const val DB_NAME = "Films.db"

        fun getDB(): DataBase {
            if (db == null) {
                synchronized(DataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            DataBase::class.java,
                            DB_NAME)
                            .build()
                    }
                }
            }
            return db!!
        }
    }
}