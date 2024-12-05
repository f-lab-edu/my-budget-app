package kr.ksw.mybudget.presentation.add.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.spending.SpendingItem

@Immutable
data class AddSpendingState(
    val item: SpendingItem = SpendingItem(),
    val showDatePickerDialog: Boolean = false,
    val showCategoryDialog: Boolean = false,
)
