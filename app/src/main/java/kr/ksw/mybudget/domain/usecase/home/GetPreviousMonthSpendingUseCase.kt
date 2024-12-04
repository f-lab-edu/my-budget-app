package kr.ksw.mybudget.domain.usecase.home

import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.domain.model.SpendingItem

interface GetPreviousMonthSpendingUseCase {
    suspend operator fun invoke(): Flow<List<SpendingItem>>
}