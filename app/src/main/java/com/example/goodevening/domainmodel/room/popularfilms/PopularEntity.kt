package com.example.goodevening.domainmodel.room.popularfilms

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.goodevening.domainmodel.utils.DateConverter
import java.util.*

@Entity
data class PopularEntity (
    @PrimaryKey
    val id: Long,
//    @TypeConverters(DateConverter::class)
    val date: Long,
    val filmName: String,
    val filmDescription: String,
    val filmYear: String,
    val filmAverage: String,
    val filmPoster : String,
    val filmGenres : String
)
