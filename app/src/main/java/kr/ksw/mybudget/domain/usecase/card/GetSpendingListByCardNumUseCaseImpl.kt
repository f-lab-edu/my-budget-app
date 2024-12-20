package kr.ksw.mybudget.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ksw.mybudget.data.repository.spending.SpendingRepository
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import javax.inject.Inject

class GetSpendingListByCardNumUseCaseImpl @Inject constructor(
    private val spendingRepository: SpendingRepository
): GetSpendingListByCardNumUseCase {
    override fun invoke(cardNum: String): Flow<List<SpendingItem>> =
        spendingRepository.getSpendingEntitiesByCardNum(cardNum).map { list ->
            list.map {
                it.toItem()
            }
        }
}