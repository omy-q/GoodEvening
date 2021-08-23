package com.example.goodevening.domainmodel.utils

import com.example.goodevening.domainmodel.*
import com.example.goodevening.room.RecentEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun convertDTOtoModel(filmsDTO: FilmDTO): CategoryFilm {
    val category = "popular"
    val films : MutableList<Film> = mutableListOf()
    val genresValue : MutableList<String> = mutableListOf()
    for (filmDTO in filmsDTO.results){
        genresValue.clear()
        for (genreID in filmDTO.genre_ids){
            genres[genreID]?.let { genresValue.add(it) }
        }
        films.add(
            Film(filmDTO.id, filmDTO.title,
            filmDTO.release_date.split("-")[0],
            filmDTO.overview,
            filmDTO.vote_average, genresValue.toList(), BASE_URL_IMAGE + filmDTO.poster_path))
    }
    return CategoryFilm(category, films.toList())
}

fun convertRecentEntityToCategoryFilm(entityList: List<RecentEntity>): CategoryFilm {
    val category = "Recently viewed"
    val type = object : TypeToken<List<String>>() {}.type
    val films: List<Film> = entityList.map{
        val genres : List<String> = Gson().fromJson(it.filmGenres, type)
        Film(it.id, it.filmName, it.filmYear,
        it.filmDescription, it.filmAverage.toFloat(), genres, it.filmPoster)
    }
    return CategoryFilm(category, films)
}

fun convertFilmToEntity(film : Film): RecentEntity {
    return RecentEntity(film.id, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}

