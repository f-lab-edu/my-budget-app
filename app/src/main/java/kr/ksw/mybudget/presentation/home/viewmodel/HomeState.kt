package kr.ksw.mybudget.presentation.home.viewmodel

import androidx.compose.runtime.Immutable
import kr.ksw.mybudget.domain.model.SpendingItem

@Immutable
data class HomeState(
    val spendingList: List<SpendingItem> = emptyList(),
    val lastSpend: Int = 0,
)