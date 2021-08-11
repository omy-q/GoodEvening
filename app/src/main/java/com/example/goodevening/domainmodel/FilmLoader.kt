package com.example.goodevening.domainmodel

import android.os.Handler
import com.example.goodevening.domainmodel.model.Facade
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class FilmLoader(
    private val listener: Facade.FilmLoaderListener?,
    private val category: String) {

    fun loadFilm() {
        val handler = Handler()
        Thread {
            try {
                val url = URL("${THE_MOVIE_DB_API_URL + category}?${THE_MOVIE_DB_API_KEY_NAME}=${THE_MOVIE_DB_API_KEY_VALUE}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
//                httpsURLConnection.addRequestProperty(
//                    THE_MOVIE_DB_API_KEY_NAME,
//                    THE_MOVIE_DB_API_KEY_VALUE
//                )
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val filmsDTO: FilmDTO = Gson().fromJson(buffer, FilmDTO::class.java)
                var films : MutableList<Film> = mutableListOf()
                for (filmDTO in filmsDTO.results){
                    films.add(Film(filmDTO.title,
                                    filmDTO.release_date,
                                    filmDTO.overview,
                                    filmDTO.vote_average))
                }
                handler.post(Runnable { listener?.onLoaded(listOf(CategoryFilm(category, films.toList()))) })
            } catch (e: Exception) {
                handler.post(Runnable { listener?.onFailed(e) })
            }
        }.start()
    }
}




