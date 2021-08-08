package com.example.goodevening.domainmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(
    val name: String = "Animal",
    val year: String = "2015",
    val country: String = "America",
    val genres: List<String> = listOf("comedy", "triller")) : Parcelable{

}

fun getFilms() = listOf(
        Film("И гастнет свет", "2016", "Соединенные Штаты", listOf("ужасы", "триллер")),
        Film("Любовь любит случайности", "2011", "Турция", listOf("драма", "мелодрама")),
        Film("Август Раш", "2017", "Соединенные Штаты", listOf("семейный", "драма")),
        Film("В метре друг от друга", "2019", "Соединенные Штаты", listOf("драма", "мелодрама")),
        Film("Путь домой", "2019", "Соединенные Штаты", listOf("семейный", "драма"),),
        Film("Звездная пыль", "2007", "Исландия", listOf("приключения", "семейный")),
        Film("Чудо", "2017", "Соединенные Штаты", listOf("семейный", "драма")),
        Film("Дальная дорога", "2015", "Соединенные Штаты", listOf("драма", "мелодрама")),
        Film("Помни", "2000", "Соединенные Штаты", listOf("детектив", "триллер")),
        Film("Апгрейд", "2018", "Австралия", listOf("ужасы", "триллер")),)




