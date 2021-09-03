package com.example.goodevening.domainmodel.utils

import com.example.goodevening.domainmodel.*
import com.example.goodevening.domainmodel.moviedb.FilmDTO
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteEntity
import com.example.goodevening.domainmodel.room.popularfilms.PopularEntity
import com.example.goodevening.domainmodel.room.recentfilms.RecentEntity
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedEntity
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun convertDTOtoModel(filmsDTO: FilmDTO): List<Film> {
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
    return films.toList()
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

fun convertFilmToRecentEntity(film : Film): RecentEntity {
    return RecentEntity(film.id, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}

fun convertFavoriteEntityToCategoryFilm(entityList: List<FavoriteEntity>): CategoryFilm {
    val category = "Favorite films"
    val type = object : TypeToken<List<String>>() {}.type
    val films: List<Film> = entityList.map{
        val genres : List<String> = Gson().fromJson(it.filmGenres, type)
        Film(it.id, it.filmName, it.filmYear,
            it.filmDescription, it.filmAverage.toFloat(), genres, it.filmPoster)
    }
    return CategoryFilm(category, films)
}

fun convertFilmToFavoriteEntity(film : Film): FavoriteEntity {
    return FavoriteEntity(film.id, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}

fun convertWatchedEntityToCategoryFilm(entityList: List<WatchedEntity>): CategoryFilm {
    val category = "Watched films"
    val type = object : TypeToken<List<String>>() {}.type
    val films: List<Film> = entityList.map{
        val genres : List<String> = Gson().fromJson(it.filmGenres, type)
        Film(it.id, it.filmName, it.filmYear,
            it.filmDescription, it.filmAverage.toFloat(), genres, it.filmPoster)
    }
    return CategoryFilm(category, films)
}

fun convertFilmToWatchedEntity(film : Film): WatchedEntity {
    return WatchedEntity(film.id, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}

fun convertWillWatchEntityToCategoryFilm(entityList: List<WillWatchEntity>): CategoryFilm {
    val category = "Will watched films"
    val type = object : TypeToken<List<String>>() {}.type
    val films: List<Film> = entityList.map{
        val genres : List<String> = Gson().fromJson(it.filmGenres, type)
        Film(it.id, it.filmName, it.filmYear,
            it.filmDescription, it.filmAverage.toFloat(), genres, it.filmPoster)
    }
    return CategoryFilm(category, films)
}

fun convertFilmToWillWatchEntity(film : Film): WillWatchEntity {
    return WillWatchEntity(film.id, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}

fun convertPopularEntityToCategoryFilm(entityList: List<PopularEntity>): CategoryFilm {
    val category = "Popular"
    val type = object : TypeToken<List<String>>() {}.type
    val films: List<Film> = entityList.map{
        val genres : List<String> = Gson().fromJson(it.filmGenres, type)
        Film(it.id, it.filmName, it.filmYear,
            it.filmDescription, it.filmAverage.toFloat(), genres, it.filmPoster)
    }
    return CategoryFilm(category, films)
}

fun convertFilmsToPopularEntity(films : List<Film>): List<PopularEntity> {
    val popularEntities : MutableList<PopularEntity> = mutableListOf()
    for (film in films){
        popularEntities.add(PopularEntity(film.id, film.name, film.description, film.year,
            film.average.toString(), film.poster, Gson().toJson(film.genres)))
    }
    return popularEntities.toList()
}

