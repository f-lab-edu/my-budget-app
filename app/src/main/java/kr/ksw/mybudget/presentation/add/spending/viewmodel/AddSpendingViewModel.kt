package kr.ksw.mybudget.presentation.add.spending.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.ksw.mybudget.R
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import kr.ksw.mybudget.domain.model.spending.SpendingType
import kr.ksw.mybudget.domain.usecase.add.spending.AddSpendingUseCase
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.toLocalDate
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val addSpendingUseCase: AddSpendingUseCase
): BaseViewModel<AddSpendingUIEffect>() {
    private val _state = MutableStateFlow(AddSpendingState())
    val state: StateFlow<AddSpendingState>
        get() = _state.asStateFlow()

    private val spendingItem: SpendingItem
        get() = state.value.item

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
            is AddSpendingActions.OnTitleChanged -> {
                updateTitle(action.title)
            }
            is AddSpendingActions.OnPriceChanged -> {
                updatePrice(action.price.ifEmpty {
                    "0"
                })
            }
            is AddSpendingActions.OnClickAddButton -> {
                if(spendingItem.title.isEmpty()) {
                    postUIEffect(AddSpendingUIEffect.ShowToastMessage(
                        R.string.main_home_spending_title_empty
                    ))
                } else {
                    viewModelScope.launch {
                        addSpendingUseCase(state.value.item)
                    }
                    postUIEffect(AddSpendingUIEffect.FinishAddScreen)
                }
            }
        }
    }

    fun initItem(item: SpendingItem?) {
        if(item != null) {
            updateItem(item)
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
            updateItem(spendingItem.copy(
                category = this
            ))
        }
    }

    private fun updateDate(date: String?) {
        showDatePicker(false)
        date?.run {
            updateItem(spendingItem.copy(
                date = this.toLocalDate()
            ))
        }
    }

    private fun updateTitle(title: String) {
        updateItem(spendingItem.copy(
            title = title
        ))
    }

    private fun updatePrice(price: String) {
        updateItem(spendingItem.copy(
            price = price.toInt()
        ))
    }

    private fun updateItem(item: SpendingItem) {
        _state.update {
            it.copy(
                item = item
            )
        }
    }
}