package kr.ksw.mybudget.presentation.card.list.viewmodel

import kr.ksw.mybudget.domain.model.card.CardItem

sealed interface CardListActions {
    data object OnClickAddIcon : CardListActions
    data class OnClickCardItem(
        val cardItem: CardItem? = null
    ) : CardListActions
    data class OnSelectedCard(
        val cardIndex: Int = 0
    ) : CardListActions
}