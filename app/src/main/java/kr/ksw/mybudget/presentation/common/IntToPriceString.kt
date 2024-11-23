package kr.ksw.mybudget.presentation.common

import java.text.DecimalFormat

fun Int.toPriceString(): String =
    DecimalFormat("###,###,###,###").format(this)