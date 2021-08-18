package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.*

class FacadeImpl(private val remoteDataSource: RemoteDataSource) : Facade {
//    private var listenerResult: Facade.FilmLoaderListener? = null
//    override fun setListener(listener: Facade.FilmLoaderListener) {
//        this.listenerResult = listener
//    }
    override fun getServerData(callback: retrofit2.Callback<FilmDTO>){
//        val filmLoader = FilmLoader(listenerResult, "popular")
//        filmLoader.loadFilm()
        remoteDataSource.loadFilm(callback)


    }
    override fun getLocalData(): List<CategoryFilm> = listOf(CategoryFilm("popular", getFilms()))
}