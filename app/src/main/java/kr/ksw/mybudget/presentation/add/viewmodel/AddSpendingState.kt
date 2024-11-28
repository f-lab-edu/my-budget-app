package kr.ksw.mybudget.presentation.add.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.SpendingItem

@Immutable
data class AddSpendingState(
    val item: SpendingItem = SpendingItem()
)
