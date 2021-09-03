package com.example.goodevening.domainmodel.room.favoritefilms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity (
    @PrimaryKey
    val id: Long,
    val filmName: String,
    val filmDescription: String,
    val filmYear: String,
    val filmAverage: String,
    val filmPoster : String,
    val filmGenres : String
)
