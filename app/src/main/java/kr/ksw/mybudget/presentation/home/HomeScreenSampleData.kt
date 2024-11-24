package kr.ksw.mybudget.presentation.home

import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.mapper.toItem
import java.time.LocalDate

val spendingList = listOf(
    SpendingEntity(
        title = "스타벅스",
        date = LocalDate.now(),
        majorCategory = 1,
        subCategory = 12,
        price = 6_000
    ),
    SpendingEntity(
        title = "할리스",
        date = LocalDate.now(),
        majorCategory = 1,
        subCategory = 12,
        price = 5_000
    ),
    SpendingEntity(
        title = "영화",
        date = LocalDate.now(),
        majorCategory = 2,
        subCategory = 22,
        price = 15_000
    ),
    SpendingEntity(
        title = "교통비",
        date = LocalDate.now(),
        majorCategory = 3,
        subCategory = 31,
        price = 100_000
    ),
    SpendingEntity(
        title = "통신비",
        date = LocalDate.now(),
        majorCategory = 3,
        subCategory = 32,
        price = 50_000
    ),
    SpendingEntity(
        title = "송금",
        date = LocalDate.now(),
        majorCategory = 4,
        subCategory = 41,
        price = 200_000
    ),
).map { it.toItem() }