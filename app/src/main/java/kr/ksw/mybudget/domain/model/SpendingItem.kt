package kr.ksw.mybudget.domain.model

import java.time.LocalDate

data class SpendingItem(
    val id: Int? = null,
    val title: String = "",
    val date: LocalDate = LocalDate.now(),
    val price: Int = 0,
    val category: SpendingType = SpendingType.UNKNOWN,
    val cardNum: String? = null,
)