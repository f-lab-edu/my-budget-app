package kr.ksw.mybudget.presentation.add.card.viewmodel

sealed class AddCardUIEffect {
    data class FinishAddScreen(val message: Int) : AddCardUIEffect()
    data class ShowToastMessage(val message: Int) : AddCardUIEffect()
}