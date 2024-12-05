package kr.ksw.mybudget.presentation.card.list.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.domain.model.spending.SpendingItem

@Immutable
data class CardListState(
    val cardList: List<CardItem>,
    val spendingList: List<SpendingItem>,
)
