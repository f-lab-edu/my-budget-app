package kr.ksw.mybudget.presentation.add.card.viewmodel

sealed interface AddCardActions {
    data class SelectCardType(val type: Int) : AddCardActions
    data class UpdateCardName(val name: String) : AddCardActions
    data class UpdateCardNumber(val number: String) : AddCardActions
    data object ClickAddButton : AddCardActions
}