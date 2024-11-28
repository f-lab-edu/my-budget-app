package kr.ksw.mybudget.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Int?): LocalDate? {
        return value?.let {  LocalDate.ofYearDay(LocalDate.now().year, value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Int? {
        return date?.dayOfYear
    }
}