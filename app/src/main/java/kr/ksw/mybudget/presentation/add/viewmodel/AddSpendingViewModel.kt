package kr.ksw.mybudget.presentation.add.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.ksw.mybudget.domain.model.SpendingType
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.toLocalDate
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(

): BaseViewModel<AddSpendingUIEffect>() {
    private val _state = MutableStateFlow(AddSpendingState())
    val state: StateFlow<AddSpendingState>
        get() = _state.asStateFlow()

    fun onAction(action: AddSpendingActions) {
        when(action) {
            is AddSpendingActions.OnClickCategoryRow -> {
                showCategoryDialog(true)
            }
            is AddSpendingActions.OnClickDateRow -> {
                showDatePicker(true)
            }
            is AddSpendingActions.OnDismissCategoryRow -> {
                updateCategory(action.category)
            }
            is AddSpendingActions.OnDismissDateRow -> {
                updateDate(action.date)
            }
        }
    }

    private fun showDatePicker(show: Boolean) {
        _state.update {
            it.copy(
                showDatePickerDialog = show
            )
        }
    }

    private fun showCategoryDialog(show: Boolean) {
        _state.update {
            it.copy(
                showCategoryDialog = show
            )
        }
    }

    private fun updateCategory(category: SpendingType?) {
        showCategoryDialog(false)
        category?.run {
            _state.update {
                it.copy(
                    item = it.item.copy(
                        category = this
                    )
                )
            }
        }
    }

    private fun updateDate(date: String?) {
        showDatePicker(false)
        date?.run {
            _state.update {
                it.copy(
                    item = it.item.copy(
                        date = this.toLocalDate()
                    )
                )
            }
        }
    }
}