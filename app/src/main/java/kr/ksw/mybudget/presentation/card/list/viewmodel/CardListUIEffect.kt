package kr.ksw.mybudget.presentation.card.list.viewmodel

import kr.ksw.mybudget.domain.model.card.CardItem

sealed class CardListUIEffect {
    data class OpenAddCardScreen(
        val cardItem: CardItem? = null
    ) : CardListUIEffect()
}