package kr.ksw.mybudget.presentation.add.card.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.ksw.mybudget.R
import kr.ksw.mybudget.domain.usecase.add.card.AddCardUseCase
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.viewModelLauncher
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase
): BaseViewModel<AddCardUIEffect>() {
    private val _state = MutableStateFlow(AddCardState())
    val state = _state.asStateFlow()

    fun onAction(action: AddCardActions) {
        when (action) {
            is AddCardActions.SelectCardType -> {
                _state.value = state.value.copy(cardItem = state.value.cardItem.copy(cardType = action.type))
            }
            is AddCardActions.UpdateCardName -> {
                _state.value = state.value.copy(cardItem = state.value.cardItem.copy(cardName = action.name))
            }
            is AddCardActions.UpdateCardNumber -> {
                _state.value = state.value.copy(cardItem = state.value.cardItem.copy(cardNumber = action.number))
            }
            is AddCardActions.ClickAddButton -> {
                val effect = if(state.value.cardItem.cardNumber.length == 16 &&
                    state.value.cardItem.cardName.isNotEmpty()) {
                    upsertCard()
                    AddCardUIEffect.FinishAddScreen(
                        message = R.string.add_card_upsert_success_message
                    )
                } else if(state.value.cardItem.cardName.isEmpty()) {
                    AddCardUIEffect.ShowToastMessage(
                        message = R.string.add_card_empty_name_error_message
                    )
                } else {
                    AddCardUIEffect.ShowToastMessage(
                        message = R.string.add_card_number_error_message
                    )
                }
                postUIEffect(effect)
            }
        }
    }

    private fun upsertCard() {
        viewModelLauncher {
            addCardUseCase(state.value.cardItem)
        }
    }
}