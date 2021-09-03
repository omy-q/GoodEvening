package com.example.goodevening.domainmodel.moviedb

import com.example.goodevening.BuildConfig
import com.example.goodevening.domainmodel.DATABASE_API_URL
import com.example.goodevening.domainmodel.REQUEST_API_KEY_VALUE
import com.google.gson.GsonBuilder
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

    fun loadFilm(callback: Callback<FilmDTO>) {
//        filmApi.getFilm(BuildConfig.THE_MOVIEDB_API_KEY).enqueue(callback)
        filmApi.getFilm(REQUEST_API_KEY_VALUE).enqueue(callback)
    }
}