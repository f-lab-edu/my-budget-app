package kr.ksw.mybudget.domain.mapper

import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.domain.model.SpendingType.*

fun SpendingEntity.toItem(): SpendingItem {
    return SpendingItem(
        id = id,
        title = title,
        date = date,
        price = price,
        category = categoryMap[subCategory] ?: UNKNOWN,
        cardNum = cardNum
    )
}

fun SpendingItem.toEntity(): SpendingEntity {
    return SpendingEntity(
        id = id,
        title = title,
        date = date,
        price = price,
        majorCategory = category.majorCategory,
        subCategory = category.subCategory,
        cardNum = cardNum
    )
}

val categoryMap = mapOf(
    11 to RESTAURANT,
    12 to CAFE,
    13 to DELIVERY,
    14 to GROCERY,
    21 to SUBSCRIPTION,
    22 to MOVIE,
    23 to BOOK,
    24 to PERFORMANCE,
    31 to TRANSPORTATION,
    32 to COMMUNICATION,
    33 to HOUSING,
    34 to HOSPITAL,
    35 to MEDICINE,
    36 to APPAREL,
    37 to EDUCATION,
    41 to TRANSFER,
)