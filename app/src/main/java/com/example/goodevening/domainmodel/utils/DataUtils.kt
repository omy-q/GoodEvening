package com.example.goodevening.domainmodel.utils

import com.example.goodevening.domainmodel.*

fun convertDTOtoModel(filmsDTO: FilmDTO): List<CategoryFilm> {
    val category = "popular"
    var films : MutableList<Film> = mutableListOf()
    var genresValue : MutableList<String> = mutableListOf()
    for (filmDTO in filmsDTO.results){
        genresValue.clear()
        for (genreID in filmDTO.genre_ids){
            genres[genreID]?.let { genresValue.add(it) }
        }
        films.add(
            Film(filmDTO.title,
            filmDTO.release_date.split("-")[0],
            filmDTO.overview,
            filmDTO.vote_average, genresValue.toList(), BASE_URL_IMAGE + filmDTO.poster_path))
    }
    return listOf(CategoryFilm(category, films.toList()))
}

