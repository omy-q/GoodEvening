package com.example.goodevening.domainmodel.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun  dateToTimestamp(date : Date?) : Long? {
       return date?.time
    }
}