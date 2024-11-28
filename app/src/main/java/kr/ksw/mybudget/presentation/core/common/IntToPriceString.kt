package kr.ksw.mybudget.presentation.core.common

import java.text.DecimalFormat

fun Int.toPriceString(): String =
    DecimalFormat("###,###,###,###").format(this)