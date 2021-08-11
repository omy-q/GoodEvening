package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.Film

interface Facade {
    fun getServerData() : Film
    // получение данных с сервера
    // временно возвращает один конктреных фильм

    fun getLocalData() : List<CategoryFilm>
    // временный метод для проверки работы
}