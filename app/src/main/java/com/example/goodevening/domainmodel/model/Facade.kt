package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.moviedb.FilmDTO

interface Facade {
    fun getServerData(callback: retrofit2.Callback<FilmDTO>)
    // получение данных с сервера
    fun getLocalData(callback: CallbackDB)
    // получение данных из локальной бд

    fun getDBRecentFilms(callbackDB: CallbackDB)
    fun getDBFavoriteFilms(callbackDB: CallbackDB)
    fun getDBWatchedFilms(callbackDB: CallbackDB)
    fun getDBWillWatchFilms(callbackDB: CallbackDB)
    fun getDBPopularFilms(callbackDB: CallbackDB)

    fun saveRecentFilm(film: Film)
    fun saveFavoriteFilm(film: Film)
    fun saveWatchedFilm(film: Film)
    fun saveWillWatchFilm(film: Film)
    fun savePopularFilms(films : List<Film>)

}