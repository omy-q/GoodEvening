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
    private val threadDB = HandlerThread("DBThread")
    private val handlerDB : Handler

    init{
        threadDB.start()
        handlerDB = Handler(threadDB.looper)
    }

    override fun getServerData(callback: retrofit2.Callback<FilmDTO>){
        remoteDataSource.loadPopularFilm(callback)
        remoteDataSource.loadComedyFilm(callback)
        remoteDataSource.loadDramaFilm(callback)
        remoteDataSource.loadThrillerFilm(callback)
        remoteDataSource.loadFantasyFilm(callback)
        remoteDataSource.loadFamilyFilm(callback)
        remoteDataSource.loadHorrorFilm(callback)
        remoteDataSource.loadWarFilm(callback)
    }

    override fun getDBRecentFilms(callbackDB: CallbackDB) {
        handlerDB.post{
            val data = convertRecentEntityToCategoryFilm(localDataSource.getRecentFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }
    }

    override fun getDBFavoriteFilms(callbackDB: CallbackDB) {
        handlerDB.post{
            val data = convertFavoriteEntityToCategoryFilm(localDataSource.getFavoriteFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }
    }

    override fun getDBWatchedFilms(callbackDB: CallbackDB) {
        handlerDB.post {
            val data = convertWatchedEntityToCategoryFilm(localDataSource.getWatchedFilms().allOderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }
    }

    override fun getDBWillWatchFilms(callbackDB: CallbackDB) {
        handlerDB.post {
            val data = convertWillWatchEntityToCategoryFilm(localDataSource.getWillWatchFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }
    }

    override fun getDBPopularFilms(callbackDB: CallbackDB) {
        handlerDB.post {
            val data = convertPopularEntityToCategoryFilm(localDataSource.getPopularFilms().allOrderByDate())
            handlerMain.post {callbackDB.onResponse(data)}
        }
    }

    override fun savePopularFilms(films: List<Film>) {
        handlerDB.post{
            localDataSource.getPopularFilms().insert(convertFilmsToPopularEntity(films))
        }
    }

    override fun saveWatchedFilm(film: Film) {
        handlerDB.post{
            localDataSource.getWatchedFilms().insert(convertFilmToWatchedEntity(film))
        }
    }

    override fun saveRecentFilm(film: Film) {
        handlerDB.post {
            localDataSource.getRecentFilms().insert(convertFilmToRecentEntity(film))
        }
    }

    override fun saveFavoriteFilm(film: Film) {
        handlerDB.post {
            localDataSource.getFavoriteFilms().insert(convertFilmToFavoriteEntity(film))
        }
    }

    override fun saveWillWatchFilm(film: Film) {
        handlerDB.post {
            localDataSource.getWillWatchFilms().insert(convertFilmToWillWatchEntity(film))
        }
    }
}