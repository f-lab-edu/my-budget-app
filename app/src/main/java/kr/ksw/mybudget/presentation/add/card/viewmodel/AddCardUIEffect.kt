package kr.ksw.mybudget.presentation.add.card.viewmodel

sealed class AddCardUIEffect {
    data object FinishAddScreen : AddCardUIEffect()
}