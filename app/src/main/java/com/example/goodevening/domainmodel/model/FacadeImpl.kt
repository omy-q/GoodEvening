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

    override fun getDBRecentFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertRecentEntityToCategoryFilm(localDataSource.getRecentFilms().all())
            callbackDB.onResponse(data)
        }.start()
    }

    override fun getDBFavoriteFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertFavoriteEntityToCategoryFilm(localDataSource.getFavoriteFilms().all())
            callbackDB.onResponse(data)
        }.start()
    }

    override fun getDBWatchedFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertWatchedEntityToCategoryFilm(localDataSource.getWatchedFilms().all())
            callbackDB.onResponse(data)
        }.start()
    }

    override fun getDBWillWatchFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertWillWatchEntityToCategoryFilm(localDataSource.getWillWatchFilms().all())
            callbackDB.onResponse(data)
        }.start()
    }

    override fun getDBPopularFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertPopularEntityToCategoryFilm(localDataSource.getPopularFilms().all())
            callbackDB.onResponse(data)
        }.start()
    }

    override fun savePopularFilms(films: List<Film>) {
        Thread {
            localDataSource.getPopularFilms().insert(convertFilmsToPopularEntity(films))
        }.start()
    }

    override fun saveWatchedFilm(film: Film) {
        Thread {
            localDataSource.getWatchedFilms().insert(convertFilmToWatchedEntity(film))
        }.start()
    }

    override fun saveRecentFilm(film: Film) {
        Thread {
            localDataSource.getRecentFilms().insert(convertFilmToRecentEntity(film))
        }.start()
    }

    override fun saveFavoriteFilm(film: Film) {
        Thread {
            localDataSource.getFavoriteFilms().insert(convertFilmToFavoriteEntity(film))
        }.start()
    }

    override fun saveWillWatchFilm(film: Film) {
        Thread {
            localDataSource.getWillWatchFilms().insert(convertFilmToWillWatchEntity(film))
        }.start()
    }
}