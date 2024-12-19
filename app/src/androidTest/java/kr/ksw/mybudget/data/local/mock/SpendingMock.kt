package kr.ksw.mybudget.data.local.mock

import kr.ksw.mybudget.data.local.entity.SpendingEntity
import java.time.LocalDate

const val MAJOR_CATEGORY_FOOD = 1
const val MAJOR_CATEGORY_CULTURE = 2
const val MAJOR_CATEGORY_LIFE_STYLE = 3
const val MAJOR_CATEGORY_CASH = 4

val spendingList = listOf(
    SpendingEntity(
        title = "스타벅스",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_FOOD,
        subCategory = 12,
        price = 6_000,
        cardNum = "4885111266651115"
    ),
    SpendingEntity(
        title = "할리스",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_FOOD,
        subCategory = 12,
        price = 5_000,
        cardNum = "4885111266651115"
    ),
    SpendingEntity(
        title = "영화",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_CULTURE,
        subCategory = 22,
        price = 15_000
    ),
    SpendingEntity(
        title = "교통비",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_LIFE_STYLE,
        subCategory = 31,
        price = 100_000,
        cardNum = "4885111266651115"
    ),
    SpendingEntity(
        title = "통신비",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_LIFE_STYLE,
        subCategory = 32,
        price = 50_000
    ),
    SpendingEntity(
        title = "송금",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_CASH,
        subCategory = 41,
        price = 200_000
    ),
)

val spendingListForBetween = listOf(
    SpendingEntity(
        title = "송금",
        date = LocalDate.now().plusWeeks(-1L),
        majorCategory = MAJOR_CATEGORY_CASH,
        subCategory = 41,
        price = 200_000
    ),
    SpendingEntity(
        title = "송금",
        date = LocalDate.now().plusMonths(-1L),
        majorCategory = MAJOR_CATEGORY_CASH,
        subCategory = 41,
        price = 200_000
    ),
    SpendingEntity(
        title = "송금",
        date = LocalDate.now(),
        majorCategory = MAJOR_CATEGORY_CASH,
        subCategory = 41,
        price = 200_000
    ),
)