package kr.ksw.mybudget.domain.usecase.home

import kr.ksw.mybudget.domain.model.SpendingItem

interface GetPreviousMonthSpendingUseCase {
    suspend operator fun invoke(): Result<List<SpendingItem>>
}