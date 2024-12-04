package kr.ksw.mybudget.domain.usecase.add

import kr.ksw.mybudget.domain.model.SpendingItem

interface AddSpendingUseCase {
    suspend operator fun invoke(item: SpendingItem)
}