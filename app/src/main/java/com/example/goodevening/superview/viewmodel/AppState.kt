package com.example.goodevening.superview.viewmodel

import com.example.goodevening.domainmodel.CategoryFilm

sealed class AppState{

    data class Success(val filmData: List<CategoryFilm>, val filmMenuData: MutableMap<String, CategoryFilm>):AppState()
//    data class SuccessLoadMenuData(val filmMenuData: MutableMap<String, CategoryFilm>):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}