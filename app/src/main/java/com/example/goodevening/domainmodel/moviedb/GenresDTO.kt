package com.example.goodevening.domainmodel.moviedb

data class GenresDTO(val genres : List<Genres>)

data class Genres(
    val id : Int,
    val name : String
)
