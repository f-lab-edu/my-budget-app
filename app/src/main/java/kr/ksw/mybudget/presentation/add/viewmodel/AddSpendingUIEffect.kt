package kr.ksw.mybudget.presentation.add.viewmodel

sealed class AddSpendingUIEffect {
    data object FinishAddScreen : AddSpendingUIEffect()
}