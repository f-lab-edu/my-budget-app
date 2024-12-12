package kr.ksw.mybudget.presentation.add.card.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.card.CardItem

@Immutable
data class AddCardState(
    val cardItem: CardItem = CardItem()
)