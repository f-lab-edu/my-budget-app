package kr.ksw.mybudget.presentation.add.spending.viewmodel

import kr.ksw.mybudget.domain.model.spending.SpendingType

sealed interface AddSpendingActions {
    data object OnClickDateRow : AddSpendingActions
    data class OnDismissDatePickerDialog(
        val date: String? = null,
    ) : AddSpendingActions
    data object OnClickCategoryRow : AddSpendingActions
    data class OnDismissCategoryDialog(
        val category: SpendingType? = null,
    ) : AddSpendingActions
    data object OnClickCardRow : AddSpendingActions
    data class OnDismissCardDialog(
        val cardIndex: Int? = null
    ) : AddSpendingActions
    data class OnTitleChanged(
        val title: String
    ) : AddSpendingActions
    data class OnPriceChanged(
        val price: String
    ) : AddSpendingActions
    data object OnClickAddButton : AddSpendingActions
}