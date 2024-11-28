package kr.ksw.mybudget.presentation.add.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(

): BaseViewModel<AddSpendingUIEffect>() {
    private val _state = MutableStateFlow(AddSpendingState())
    val state: StateFlow<AddSpendingState>
        get() = _state.asStateFlow()


}