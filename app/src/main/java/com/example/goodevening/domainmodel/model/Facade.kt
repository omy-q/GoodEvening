package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.moviedb.FilmDTO

interface Facade {
    fun getServerData(callback: retrofit2.Callback<FilmDTO>)
    // получение данных с сервера

    fun getDBRecentFilms() : CategoryFilm

    fun saveRecentFilm(film: Film)

    fun getDBFavoriteFilms() : CategoryFilm

    fun saveFavoriteFilm(film: Film)

    fun getDBWatchedFilms() : CategoryFilm

    fun saveWatchedFilm(film: Film)

    fun getDBWillWatchFilms() : CategoryFilm

    fun saveWillWatchFilm(film: Film)

    fun getDBPopularFilms() : CategoryFilm

    fun savePopularFilms(films : List<Film>)

}