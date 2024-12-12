package kr.ksw.mybudget.presentation.card.list.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.domain.usecase.add.card.GetAllCardUseCase
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.viewModelLauncher
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val getAllCardUseCase: GetAllCardUseCase
): BaseViewModel<CardListUIEffect>() {
    private val _state = MutableStateFlow(CardListState())
    val state = _state.asStateFlow()

    init {
        viewModelLauncher {
            getAllCardUseCase().collectLatest {
                updateCardList(it)
            }
        }
    }

    fun onAction(action: CardListActions) {
        when(action) {
            is CardListActions.OnClickAddIcon,
            is CardListActions.OnClickCardItem -> {
                postUIEffect(
                    CardListUIEffect.OpenAddCardScreen(
                        if(action is CardListActions.OnClickCardItem) {
                            action.cardItem
                        } else {
                            null
                        }
                    )
                )
            }
            is CardListActions.OnSelectedCard -> {
                _state.update {
                    it.copy(
                        selectedCardIndex = action.cardIndex
                    )
                }
            }
        }
    }

    private fun updateCardList(
        cardList: List<CardItem>
    ) {
        _state.update {
            it.copy(
                cardList = cardList
            )
        }
    }
}