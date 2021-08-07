package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.Film

class FacadeImpl : Facade {
    override fun getServerData(): Film {
        return Film()
    }

    override fun getLocalData(): Film {
        return Film()
    }
}