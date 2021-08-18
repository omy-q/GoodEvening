package com.example.goodevening.domainmodel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FilmAPI {
    @GET("movie/popular")
    fun getFilm(@Query(REQUEST_API_KEY_NAME) token: String): Call<FilmDTO>
}