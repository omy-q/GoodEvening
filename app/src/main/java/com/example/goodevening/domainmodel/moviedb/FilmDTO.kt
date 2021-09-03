package com.example.goodevening.domainmodel.moviedb

data class FilmDTO(val results: List<ResultDTO>) {
}

data class ResultDTO(
    val id : Long,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Float,
    val genre_ids : List<Int>,
    val poster_path : String
)

