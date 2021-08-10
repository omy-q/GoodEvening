package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.Film
import com.example.goodevening.domainmodel.getFilms

class FacadeImpl : Facade {
    override fun getServerData() = Film()
    override fun getLocalData() = getFilms()
}