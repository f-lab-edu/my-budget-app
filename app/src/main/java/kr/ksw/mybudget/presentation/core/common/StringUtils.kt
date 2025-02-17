package kr.ksw.mybudget.presentation.core.common

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DATE_FORMAT_YMD = "yyyy.MM.dd"
const val DATE_FORMAT_YMD_E = "yyyy.MM.dd (EE)"
const val DATE_FORMAT_YMD_ADD = "yyyy/MM/dd EE요일"

fun Int.toPriceString(): String =
    DecimalFormat("###,###,###,###").format(this)

fun LocalDate.toDisplayString(
    formatter: String
): String = format(DateTimeFormatter.ofPattern(formatter))

fun String.toLocalDate(
    formatter: String = DATE_FORMAT_YMD_ADD
): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern(DATE_FORMAT_YMD_ADD))
}