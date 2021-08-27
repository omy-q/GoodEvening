package com.example.goodevening.domainmodel.room.watchedfilms

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchedEntity (
    @PrimaryKey
    val id: Long,
    val filmName: String,
    val filmDescription: String,
    val filmYear: String,
    val filmAverage: String,
    val filmPoster : String,
    val filmGenres : String
)
