package com.example.goodevening.domainmodel.room.facade

import com.example.goodevening.app.App
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteDAO
import com.example.goodevening.domainmodel.room.genres.GenresDAO
import com.example.goodevening.domainmodel.room.popularfilms.PopularDAO
import com.example.goodevening.domainmodel.room.recentfilms.RecentDAO
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedDAO
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchDAO

interface RoomFacade {
    fun getGenres() : GenresDAO
    fun getFavoriteFilms() : FavoriteDAO
    fun getPopularFilms() : PopularDAO
    fun getRecentFilms() : RecentDAO
    fun getWatchedFilms() : WatchedDAO
    fun getWillWatchFilms() : WillWatchDAO
}