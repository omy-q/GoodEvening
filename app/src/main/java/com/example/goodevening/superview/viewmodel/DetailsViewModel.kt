package com.example.goodevening.superview.viewmodel

import androidx.lifecycle.ViewModel
import com.example.goodevening.app.App.Companion.getDB
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.model.Facade
import com.example.goodevening.domainmodel.model.FacadeImpl
import com.example.goodevening.domainmodel.moviedb.RemoteDataSource
import com.example.goodevening.domainmodel.room.facade.RoomFacadeImpl

class DetailsViewModel(
    private val facade : Facade = FacadeImpl(RemoteDataSource(), RoomFacadeImpl(getDB()))
) : ViewModel() {

    fun saveFavoriteFilmToDB(film : Film) {
        facade.saveFavoriteFilm(film)
    }

    fun saveWatchedFilmToDB(film : Film) {
        facade.saveWatchedFilm(film)
    }

    fun saveWillWatchedFilmToDB(film : Film) {
        facade.saveWillWatchFilm(film)
    }
}