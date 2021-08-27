package com.example.goodevening.domainmodel.room.facade

import com.example.goodevening.app.App
import com.example.goodevening.domainmodel.room.DataBase
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteDAO
import com.example.goodevening.domainmodel.room.genres.GenresDAO
import com.example.goodevening.domainmodel.room.popularfilms.PopularDAO
import com.example.goodevening.domainmodel.room.recentfilms.RecentDAO
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedDAO
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchDAO

class RoomFacadeImpl(private val db : DataBase) : RoomFacade {
    override fun getGenres(): GenresDAO = db.genresDao()

    override fun getFavoriteFilms(): FavoriteDAO = db.favoriteDao()

    override fun getPopularFilms(): PopularDAO = db.popularDao()

    override fun getRecentFilms(): RecentDAO = db.recentDao()

    override fun getWatchedFilms(): WatchedDAO = db.watchedDao()

    override fun getWillWatchFilms(): WillWatchDAO = db.willwatchDao()
}