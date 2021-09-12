package com.example.goodevening.domainmodel.model

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import com.example.goodevening.domainmodel.*
import com.example.goodevening.domainmodel.moviedb.FilmDTO
import com.example.goodevening.domainmodel.moviedb.RemoteDataSource
import com.example.goodevening.domainmodel.room.facade.RoomFacade
import com.example.goodevening.domainmodel.utils.*


class FacadeImpl(private val remoteDataSource: RemoteDataSource,
                 private val localDataSource: RoomFacade
) : Facade {

    private val handlerMain = Handler(Looper.getMainLooper())

    override fun getServerData(callback: retrofit2.Callback<FilmDTO>){
        Thread {
            remoteDataSource.loadPopularFilm(callback)
            remoteDataSource.loadComedyFilm(callback)
            remoteDataSource.loadDramaFilm(callback)
            remoteDataSource.loadThrillerFilm(callback)
            remoteDataSource.loadFantasyFilm(callback)
            remoteDataSource.loadFamilyFilm(callback)
            remoteDataSource.loadHorrorFilm(callback)
            remoteDataSource.loadWarFilm(callback)
        }.start()
    }

    override fun getLocalData(callback: CallbackDB) {
        Thread {
            getDBRecentFilms(callback)
            getDBFavoriteFilms(callback)
            getDBWatchedFilms(callback)
            getDBWillWatchFilms(callback)
        }.start()

    }

    override fun getDBRecentFilms(callbackDB: CallbackDB) {
        Thread {
            val data =
                convertRecentEntityToCategoryFilm(localDataSource.getRecentFilms().allOrderByDate())
            handlerMain.post { callbackDB.onResponse(data) }
        }.start()
    }

    override fun getDBFavoriteFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertFavoriteEntityToCategoryFilm(localDataSource.getFavoriteFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }.start()
    }

    override fun getDBWatchedFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertWatchedEntityToCategoryFilm(localDataSource.getWatchedFilms().allOderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }.start()
    }

    override fun getDBWillWatchFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertWillWatchEntityToCategoryFilm(localDataSource.getWillWatchFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }.start()
    }

    override fun getDBPopularFilms(callbackDB: CallbackDB) {
        Thread {
            val data = convertPopularEntityToCategoryFilm(localDataSource.getPopularFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
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