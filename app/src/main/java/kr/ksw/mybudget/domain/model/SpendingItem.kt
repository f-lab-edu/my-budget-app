package kr.ksw.mybudget.domain.model

import java.time.LocalDate

data class SpendingItem(
    val id: Int?,
    val title: String,
    val date: LocalDate,
    val price: Int,
    val category: SpendingType,
    val cardNum: String? = null,
)