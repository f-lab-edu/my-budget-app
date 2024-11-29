package kr.ksw.mybudget.presentation.add.viewmodel

import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.domain.model.SpendingType

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
    data class OnClickAddButton(
        val item: SpendingItem
    ) : AddSpendingActions
}