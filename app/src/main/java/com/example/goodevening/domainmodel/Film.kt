package com.example.goodevening.domainmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(
    val name: String = "Animal",
    val year: String = "2015",
    val description: String = "default",
    val average : Float = 10.0f
//    val country: String = "America",
//    val genres: List<String> = listOf("comedy", "triller"),
) : Parcelable{

}

fun getFilms() = listOf(
        Film("И гастнет свет", "2016", "Соединенные Штаты"),
        Film("Любовь любит случайности", "2011", "Турция"),
        Film("Август Раш", "2017", "Соединенные Штаты"),
        Film("В метре друг от друга", "2019", "Соединенные Штаты"),
        Film("Путь домой", "2019", "Соединенные Штаты"),
        Film("Звездная пыль", "2007", "Исландия"),
        Film("Чудо", "2017", "Соединенные Штаты"),
        Film("Дальная дорога", "2015", "Соединенные Штаты"),
        Film("Помни", "2000", "Соединенные Штаты"),
        Film("Апгрейд", "2018", "Австралия"))




