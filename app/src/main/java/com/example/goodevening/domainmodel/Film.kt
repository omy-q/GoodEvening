package com.example.goodevening.domainmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(
    val id : Long,
    val name: String,
    val year: String,
    val description: String,
    val average : Float,
    val genres : List<String>,
    val poster : String
) : Parcelable{}
val genres = mapOf<Int, String>(28 to "Action", 12 to "Adventure", 16 to "Animation",
    35 to "Comedy", 80 to "Crime", 99 to "Documentary",
    18 to "Drama", 10751 to "Family", 14 to "Fantasy",
    36 to "History", 27 to "Horror", 10402 to "Music",
    9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
    10770 to "TV Movie", 53 to "Thriller", 10752 to "War", 37 to "Western")


fun getFilms() = listOf(
        Film(11, "И гастнет свет", "2016", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(12,"Любовь любит случайности", "2011", "Турция", 10.0f, listOf("default"), "default"),
        Film(13,"Август Раш", "2017", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(14,"В метре друг от друга", "2019", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(15,"Путь домой", "2019", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(16,"Звездная пыль", "2007", "Исландия", 10.0f, listOf("default"), "default"),
        Film(17,"Чудо", "2017", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(18,"Дальная дорога", "2015", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(19,"Помни", "2000", "Соединенные Штаты", 10.0f, listOf("default"), "default"),
        Film(10,"Апгрейд", "2018", "Австралия", 10.0f, listOf("default"), "default"))




