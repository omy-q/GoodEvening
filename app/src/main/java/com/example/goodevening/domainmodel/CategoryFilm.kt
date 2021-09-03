package com.example.goodevening.domainmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryFilm(val category:String, val films:List<Film>) : Parcelable
