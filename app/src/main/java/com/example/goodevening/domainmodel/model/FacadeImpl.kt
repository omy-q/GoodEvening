package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.*
import com.example.goodevening.domainmodel.moviedb.FilmDTO
import com.example.goodevening.domainmodel.moviedb.RemoteDataSource
import com.example.goodevening.domainmodel.room.facade.RoomFacade
import com.example.goodevening.domainmodel.utils.*

class FacadeImpl(private val remoteDataSource: RemoteDataSource,
                 private val localDataSource: RoomFacade
) : Facade {

    override fun getServerData(callback: retrofit2.Callback<FilmDTO>){
        remoteDataSource.loadFilm(callback)
    }

    override fun getDBRecentFilms(): CategoryFilm {
        return convertRecentEntityToCategoryFilm(localDataSource.getRecentFilms().all())
    }

    override fun saveRecentFilm(film: Film) {
        localDataSource.getRecentFilms().insert(convertFilmToRecentEntity(film))

    }

    override fun getDBFavoriteFilms(): CategoryFilm {
        return convertFavoriteEntityToCategoryFilm(localDataSource.getFavoriteFilms().all())
    }

    override fun saveFavoriteFilm(film: Film) {
        localDataSource.getFavoriteFilms().insert(convertFilmToFavoriteEntity(film))
    }

    override fun getDBWatchedFilms(): CategoryFilm {
        return convertWatchedEntityToCategoryFilm(localDataSource.getWatchedFilms().all())
    }

    override fun saveWatchedFilm(film: Film) {
        localDataSource.getWatchedFilms().insert(convertFilmToWatchedEntity(film))
    }

    override fun getDBWillWatchFilms(): CategoryFilm {
        return convertWillWatchEntityToCategoryFilm(localDataSource.getWillWatchFilms().all())
    }

    override fun saveWillWatchFilm(film: Film) {
        localDataSource.getWillWatchFilms().insert(convertFilmToWillWatchEntity(film))
    }

    override fun getDBPopularFilms(): CategoryFilm {
        return convertPopularEntityToCategoryFilm(localDataSource.getPopularFilms().all())
    }

    override fun savePopularFilms(films: List<Film>) {
        localDataSource.getPopularFilms().insert(convertFilmsToPopularEntity(films))
    }
}