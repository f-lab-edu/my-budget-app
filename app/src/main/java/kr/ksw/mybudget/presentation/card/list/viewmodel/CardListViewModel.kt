package kr.ksw.mybudget.presentation.card.list.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import kr.ksw.mybudget.domain.usecase.add.card.GetAllCardUseCase
import kr.ksw.mybudget.domain.usecase.home.GetMonthlySpendingUseCase
import kr.ksw.mybudget.presentation.core.common.BaseViewModel
import kr.ksw.mybudget.presentation.core.common.viewModelLauncher
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val getAllCardUseCase: GetAllCardUseCase,
    private val getMonthlySpendingUseCase: GetMonthlySpendingUseCase
): BaseViewModel<CardListState, CardListUIEffect>(CardListState()) {

    init {
        viewModelLauncher {
            getAllCardUseCase().collectLatest {
                updateCardList(it)
            }
        }
        viewModelLauncher {
            getMonthlySpendingUseCase().collectLatest {
                updateSpendingList(it)
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
                updateState {
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
        updateState {
            it.copy(
                cardList = cardList
            )
        }
    }

    private fun updateSpendingList(
        spendingList: List<SpendingItem>
    ) {
        updateState {
            it.copy(
                spendingList = spendingList
            )
        }
    }
}