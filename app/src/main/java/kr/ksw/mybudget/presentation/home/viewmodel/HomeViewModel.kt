package kr.ksw.mybudget.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCase
import kr.ksw.mybudget.domain.usecase.home.GetPreviousMonthSpendingUseCase
import kr.ksw.mybudget.presentation.core.common.viewModelLauncher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMonthlySpendingUseCase: GetMonthlySpendingUseCase,
    private val getPreviousMonthSpendingUseCase: GetPreviousMonthSpendingUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        viewModelLauncher {
            getMonthlySpendingUseCase().collectLatest { items ->
                updateState(
                    state.value.copy(
                        spendingList = items.sortedByDescending { item ->
                            item.date
                        }
                    )
                )
            }
        }
        viewModelLauncher {
            getPreviousMonthSpendingUseCase().collectLatest { items ->
                updateState(
                    state.value.copy(
                        lastSpend = items.sumOf {
                            it.price
                        }
                    )
                )
            }
        }
    }

    private fun updateState(
        state: HomeState
    ) {
        _state.update {
            state
        }
    }
}