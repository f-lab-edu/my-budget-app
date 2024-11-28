package kr.ksw.mybudget.presentation.core.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

open class BaseViewModel<EFFECT> : ViewModel() {
    private val _uiEffect = MutableSharedFlow<EFFECT>(replay = 0)
    val uiEffect: SharedFlow<EFFECT>
        get() = _uiEffect.asSharedFlow()
}