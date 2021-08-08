package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.getFilms

class FacadeImpl : Facade {
    override fun getServerData(): Film {
        return Film()
    }

    override fun getLocalData(): List<Film> {
        return getFilms()
    }
}