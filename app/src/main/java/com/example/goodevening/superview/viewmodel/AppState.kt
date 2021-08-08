package com.example.goodevening.superview.viewmodel

import com.example.goodevening.domainmodel.Film

sealed class AppState{

    data class Success(val filmData: List<Film>):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}