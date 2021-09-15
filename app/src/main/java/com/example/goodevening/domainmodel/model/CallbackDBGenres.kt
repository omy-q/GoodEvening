package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.room.genres.GenresEntity

interface CallbackDBGenres {
    fun onResponse(result : List<GenresEntity>)
}