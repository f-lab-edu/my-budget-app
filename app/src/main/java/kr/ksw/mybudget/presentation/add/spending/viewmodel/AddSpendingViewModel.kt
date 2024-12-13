package kr.ksw.mybudget.presentation.add.spending.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.ksw.mybudget.R
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import kr.ksw.mybudget.domain.model.spending.SpendingType
import kr.ksw.mybudget.domain.usecase.add.card.GetAllCardUseCase
import kr.ksw.mybudget.domain.usecase.add.spending.AddSpendingUseCase
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.toLocalDate
import kr.ksw.mybudget.presentation.core.common.viewModelLauncher
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val addSpendingUseCase: AddSpendingUseCase,
    private val getAllCardUseCase: GetAllCardUseCase
): BaseViewModel<AddSpendingUIEffect>() {
    private val _state = MutableStateFlow(AddSpendingState())
    val state: StateFlow<AddSpendingState>
        get() = _state.asStateFlow()

    private val spendingItem: SpendingItem
        get() = state.value.item

    fun onAction(action: AddSpendingActions) {
        when(action) {
            is AddSpendingActions.OnClickCategoryRow,
            is AddSpendingActions.OnClickDateRow,
            is AddSpendingActions.OnClickCardRow-> {
                showDialog(action = action)
            }
            is AddSpendingActions.OnDismissCategoryDialog -> {
                updateCategory(action.category)
            }
            is AddSpendingActions.OnDismissDatePickerDialog -> {
                updateDate(action.date)
            }
            is AddSpendingActions.OnDismissCardDialog -> {
                updateCardIndex(action.cardIndex)
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
        viewModelLauncher {
            getAllCardUseCase().collectLatest { cardList ->
                _state.update {
                    it.copy(
                        cardList = cardList,
                        selectedCardIndex = item?.run {
                            val index = cardList.indexOfFirst { cardItem ->
                                cardItem.cardNumber == this.cardNum
                            }
                            if(index == -1) {
                                0
                            } else {
                                index
                            }
                        } ?: 0
                    )
                }
            }
        }
    }

    private fun showDialog(
        action: AddSpendingActions,
        show: Boolean = true
    ) {
        _state.update {
            when(action) {
                is AddSpendingActions.OnClickCategoryRow -> it.copy(
                    showCategoryDialog = show
                )
                is AddSpendingActions.OnClickCardRow -> it.copy(
                    showCardDialog = show
                )
                else -> it.copy(
                    showDatePickerDialog = show
                )
            }
        }
    }

    private fun updateDate(date: String?) {
        showDialog(
            action = AddSpendingActions.OnClickDateRow,
            show = false
        )
        date?.run {
            updateItem(spendingItem.copy(
                date = this.toLocalDate()
            ))
        }
    }

    private fun updateCategory(category: SpendingType?) {
        showDialog(
            action = AddSpendingActions.OnClickCategoryRow,
            show = false
        )
        category?.run {
            updateItem(spendingItem.copy(
                category = this
            ))
        }
    }

    private fun updateCardIndex(cardIndex: Int?) {
        showDialog(
            action = AddSpendingActions.OnClickCardRow,
            show = false
        )
        cardIndex?.run {
            _state.update {
                it.copy(
                    selectedCardIndex = this,
                    item = it.item.copy(
                        cardNum = it.cardList[this].cardNumber
                    )
                )
            }
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