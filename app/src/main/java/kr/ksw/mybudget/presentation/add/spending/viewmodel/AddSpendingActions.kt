package kr.ksw.mybudget.presentation.add.spending.viewmodel

import kr.ksw.mybudget.domain.model.spending.SpendingType

sealed interface AddSpendingActions {
    data object OnClickDateRow : AddSpendingActions
    data class OnDismissDateRow(
        val date: String? = null,
    ) : AddSpendingActions
    data object OnClickCategoryRow : AddSpendingActions
    data class OnDismissCategoryRow(
        val category: SpendingType? = null,
    ) : AddSpendingActions
    data class OnTitleChanged(
        val title: String
    ) : AddSpendingActions
    data class OnPriceChanged(
        val price: String
    ) : AddSpendingActions
    data object OnClickAddButton : AddSpendingActions
}