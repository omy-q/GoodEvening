package com.example.goodevening.domainmodel.model

import com.example.goodevening.domainmodel.CategoryFilm

interface CallbackDB {
    fun onResponse(result : CategoryFilm)
}