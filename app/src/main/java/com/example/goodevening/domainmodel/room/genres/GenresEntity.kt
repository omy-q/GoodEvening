package com.example.goodevening.domainmodel.room.genres

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenresEntity (
    @PrimaryKey
    val id : Long,
    val genre : String )