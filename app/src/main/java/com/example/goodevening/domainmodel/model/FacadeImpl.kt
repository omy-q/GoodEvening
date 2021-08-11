package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.*

class FacadeImpl : Facade {
    private var listenerResult: Facade.FilmLoaderListener? = null
    override fun setListener(listener: Facade.FilmLoaderListener) {
        this.listenerResult = listener
    }
    override fun getServerData(){
        val filmLoader = FilmLoader(listenerResult, "popular")
        filmLoader.loadFilm()
    }
    override fun getLocalData(): List<CategoryFilm> = listOf(CategoryFilm("popular", getFilms()))
}