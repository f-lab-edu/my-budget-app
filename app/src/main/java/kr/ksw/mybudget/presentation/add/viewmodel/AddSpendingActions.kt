package kr.ksw.mybudget.presentation.add.viewmodel

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
}