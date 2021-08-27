package com.example.goodevening.domainmodel.moviedb

import com.example.goodevening.domainmodel.REQUEST_API_KEY_NAME
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {
    @GET("movie/popular")
    fun getFilm(@Query(REQUEST_API_KEY_NAME) token: String): Call<FilmDTO>
}