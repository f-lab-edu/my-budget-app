package kr.ksw.mybudget.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.domain.model.spending.SpendingItem

interface GetSpendingListByCardNumUseCase {
    operator fun invoke(cardNum: String): Flow<List<SpendingItem>>
}