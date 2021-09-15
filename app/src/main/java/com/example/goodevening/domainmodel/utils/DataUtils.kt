package com.example.goodevening.domainmodel.utils

import com.example.goodevening.domainmodel.*
import com.example.goodevening.domainmodel.moviedb.FilmDTO
import com.example.goodevening.domainmodel.moviedb.GenresDTO
import com.example.goodevening.domainmodel.room.favoritefilms.FavoriteEntity
import com.example.goodevening.domainmodel.room.genres.GenresEntity
import com.example.goodevening.domainmodel.room.popularfilms.PopularEntity
import com.example.goodevening.domainmodel.room.recentfilms.RecentEntity
import com.example.goodevening.domainmodel.room.watchedfilms.WatchedEntity
import com.example.goodevening.domainmodel.room.willwatchfilms.WillWatchEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


fun convertDTOtoModel(category : String, filmsDTO: FilmDTO): CategoryFilm {
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
    return CategoryFilm(category, films)
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

fun convertFilmToRecentEntity(film : Film): RecentEntity {
    val date: Date = Calendar.getInstance().time
    return RecentEntity(film.id, date.time, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}
fun convertFilmToFavoriteEntity(film : Film): FavoriteEntity {
    val date: Date = Calendar.getInstance().time
    return FavoriteEntity(film.id, date.time, film.name,  film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}
fun convertFilmToWatchedEntity(film : Film): WatchedEntity {
    val date: Date = Calendar.getInstance().time
    return WatchedEntity(film.id, date.time, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}
fun convertFilmToWillWatchEntity(film : Film): WillWatchEntity {
    val date: Date = Calendar.getInstance().time
    return WillWatchEntity(film.id, date.time, film.name, film.description, film.year,
        film.average.toString(), film.poster, Gson().toJson(film.genres))
}
fun convertFilmsToPopularEntity(films : List<Film>): List<PopularEntity> {
    val popularEntities : MutableList<PopularEntity> = mutableListOf()
    val date: Date = Calendar.getInstance().time
    for (film in films){
        popularEntities.add(PopularEntity(film.id, date.time, film.name, film.description, film.year,
            film.average.toString(), film.poster, Gson().toJson(film.genres)))
    }
    return popularEntities.toList()
}

fun convertDTOToMap(genresDTO : GenresDTO) : Map<Int, String> {
    val genres = mutableMapOf<Int, String>()
    genresDTO.genres.forEach {
        genres[it.id] = it.name
    }
    return genres.toMap()
}

fun convertEntityToMap(genresEntity : List<GenresEntity>) : Map<Int, String> {
    val genres = mutableMapOf<Int, String>()
    genresEntity.forEach {
        genres[it.id.toInt()] = it.genre
    }
    return genres.toMap()
}

fun convertToGenreEntity(genres : Map<Int, String>) : List<GenresEntity> {
    val genresEntity = mutableListOf<GenresEntity>()
    genres.keys.forEach { key ->
        genres[key]?.let { genresEntity.add(GenresEntity(key, it)) }
    }
    return genresEntity
}












