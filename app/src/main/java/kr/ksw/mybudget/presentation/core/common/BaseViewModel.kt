package kr.ksw.mybudget.presentation.core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<STATE, EFFECT>(
    initialState: STATE
) : ViewModel() {
    private val _uiEffect = MutableSharedFlow<EFFECT>(replay = 0)
    val uiEffect: SharedFlow<EFFECT>
        get() = _uiEffect.asSharedFlow()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE>
        get() = _state.asStateFlow()

    protected fun updateState(reducer: (STATE) -> STATE) {
        _state.update {
            reducer(it)
        }
    }

    protected fun postUIEffect(effect: EFFECT) {
        viewModelScope.launch {
            _uiEffect.emit(effect)
        }
    }
}