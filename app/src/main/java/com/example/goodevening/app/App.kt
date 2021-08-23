package com.example.goodevening.app

import android.app.Application
import androidx.room.Room
import com.example.goodevening.room.RecentDAO
import com.example.goodevening.room.RecentDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: RecentDataBase? = null
        private const val DB_NAME = "PopularFilms.db"

        fun getFilmDao(): RecentDAO {
            if (db == null) {
                synchronized(RecentDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            RecentDataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.popularDao()
        }
    }
}