package com.example.goodevening.domainmodel

import android.os.Parcelable

data class FilmDTO(val results: List<ResultDTO>) {
}

data class ResultDTO(
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Float
)

