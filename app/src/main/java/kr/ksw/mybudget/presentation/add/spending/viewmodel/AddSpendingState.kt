package kr.ksw.mybudget.presentation.add.spending.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.domain.model.spending.SpendingItem

@Immutable
data class AddSpendingState(
    val item: SpendingItem = SpendingItem(),
    val cardList: List<CardItem> = emptyList(),
    val selectedCardIndex: Int = 0,
    val showDatePickerDialog: Boolean = false,
    val showCategoryDialog: Boolean = false,
    val showCardDialog: Boolean = false,
)
