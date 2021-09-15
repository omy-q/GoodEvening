package com.example.goodevening.domainmodel.moviedb

import com.example.goodevening.BuildConfig
import com.example.goodevening.domainmodel.*
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val filmApi = Retrofit.Builder()
        .baseUrl(DATABASE_API_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun loadPopularFilm(callback: Callback<FilmDTO>) {
//        filmApi.getPopularFilm(BuildConfig.THE_MOVIEDB_API_KEY).enqueue(callback)
        filmApi.getPopularFilm(REQUEST_API_KEY_VALUE).enqueue(callback)
    }

    fun loadComedyFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            COMEDY_ID).enqueue(callback)
    }

    fun loadHorrorFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            HORROR_ID).enqueue(callback)
    }

    fun loadThrillerFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            THRILLER_ID).enqueue(callback)
    }

    fun loadFamilyFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            FAMILY_ID).enqueue(callback)
    }

    fun loadWarFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            WAR_ID).enqueue(callback)
    }

    fun loadDramaFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            DRAMA_ID).enqueue(callback)
    }

    fun loadFantasyFilm(callback: Callback<FilmDTO>){
        filmApi.getFilmByGenre(
            REQUEST_API_KEY_VALUE,
            FANTASY_ID).enqueue(callback)
    }

    fun loadGenres(callback: Callback<GenresDTO>){
        filmApi.getGenres(REQUEST_API_KEY_VALUE).enqueue(callback)
    }
}