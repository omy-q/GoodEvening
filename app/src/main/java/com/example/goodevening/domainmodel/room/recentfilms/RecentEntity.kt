package com.example.goodevening.domainmodel.room.recentfilms

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.goodevening.domainmodel.utils.DateConverter
import java.util.*

@Entity
data class RecentEntity(
    @PrimaryKey
    val id: Long,
//    @field:TypeConverters(DateConverter::class)
    val date: Long,
    val filmName: String,
    val filmDescription: String,
    val filmYear: String,
    val filmAverage: String,
    val filmPoster : String,
    val filmGenres : String
)
