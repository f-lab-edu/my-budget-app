package kr.ksw.mybudget.presentation.add.spending.viewmodel

sealed class AddSpendingUIEffect {
    data object FinishAddScreen : AddSpendingUIEffect()
    data class ShowToastMessage(val message: Int) : AddSpendingUIEffect()
}