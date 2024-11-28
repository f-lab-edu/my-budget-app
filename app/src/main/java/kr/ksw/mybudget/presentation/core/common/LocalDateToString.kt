package kr.ksw.mybudget.presentation.core.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DATE_FORMAT_YMD = "yyyy.MM.dd"
const val DATE_FORMAT_YMD_E = "yyyy.MM.dd (EE)"
const val DATE_FORMAT_YMD_ADD = "yyyy/MM/dd EE요일"

fun LocalDate.toDisplayString(
    formatter: String
): String = format(DateTimeFormatter.ofPattern(formatter))