package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.CategoryFilm
import com.example.goodevening.domainmodel.Film

interface Facade {
    fun getServerData()
    // получение данных с сервера
    // временно возвращает один конктреных фильм

    fun getLocalData() : List<CategoryFilm>
    // временный метод для проверки работы

    fun setListener(listener: FilmLoaderListener)

    interface FilmLoaderListener {
        fun onLoaded(categoriesFilm : List<CategoryFilm>)
        fun onFailed(throwable: Throwable)
    }
}