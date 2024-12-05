package kr.ksw.mybudget.domain.usecase.add

import kr.ksw.mybudget.domain.model.spending.SpendingItem

interface AddSpendingUseCase {
    suspend operator fun invoke(item: SpendingItem)
}