package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.*
import com.example.goodevening.domainmodel.utils.convertFilmToEntity
import com.example.goodevening.domainmodel.utils.convertRecentEntityToCategoryFilm
import com.example.goodevening.room.RecentDAO

class FacadeImpl(private val remoteDataSource: RemoteDataSource,
                 private val localDataSource: RecentDAO) : Facade {

    override fun getServerData(callback: retrofit2.Callback<FilmDTO>){
        remoteDataSource.loadFilm(callback)
    }

    override fun getLocalData(): List<CategoryFilm> = listOf(CategoryFilm("popular", getFilms()))


    override fun getDBRecentData(): CategoryFilm {
        return convertRecentEntityToCategoryFilm(localDataSource.all())
    }

    override fun saveRecentFilm(film: Film) {
        localDataSource.insert(convertFilmToEntity(film))

    }
}