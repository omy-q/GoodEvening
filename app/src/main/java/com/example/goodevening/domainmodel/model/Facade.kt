package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.moviedb.FilmDTO

interface Facade {
    fun getServerData(callback: retrofit2.Callback<FilmDTO>)
    // получение данных с сервера
    // временно возвращает один конктреных фильм

    fun getLocalData() : List<CategoryFilm>
    // временный метод для проверки работы

    fun getDBRecentData() : CategoryFilm

    fun saveRecentFilm(film: Film)

}