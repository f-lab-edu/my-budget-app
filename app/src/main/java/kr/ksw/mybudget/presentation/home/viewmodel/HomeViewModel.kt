package kr.ksw.mybudget.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCase
import kr.ksw.mybudget.domain.usecase.home.GetPreviousMonthSpendingUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMonthlySpendingUseCase: GetMonthlySpendingUseCase,
    private val getPreviousMonthSpendingUseCase: GetPreviousMonthSpendingUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getMonthlySpendingUseCase().collectLatest { items ->
                _state.update {
                    it.copy(
                        spendingList = items.sortedByDescending { item ->
                            item.date
                        }
                    )
                }
            }
        }
        viewModelScope.launch {
            getPreviousMonthSpendingUseCase().getOrNull()?.run {
                val spend = sumOf {
                    it.price
                }
                _state.update {
                    it.copy(
                        lastSpend = spend
                    )
                }
            }
        }
    }
}