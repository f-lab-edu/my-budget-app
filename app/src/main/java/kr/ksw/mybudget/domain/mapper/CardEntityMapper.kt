package kr.ksw.mybudget.domain.mapper

import kr.ksw.mybudget.data.local.entity.CardEntity
import kr.ksw.mybudget.domain.model.card.CardItem

fun CardEntity.toItem() = CardItem(
    id = id ?: -1,
    cardName = cardName,
    cardNumber = cardNumber,
    cardColor = cardColor,
    cardType = cardType
)

fun CardItem.toEntity() = CardEntity(
    id = id,
    cardName = cardName,
    cardNumber = cardNumber,
    cardColor = cardColor,
    cardType = cardType
)