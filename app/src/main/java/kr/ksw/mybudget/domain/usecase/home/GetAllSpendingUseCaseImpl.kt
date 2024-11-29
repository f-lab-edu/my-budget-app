package kr.ksw.mybudget.domain.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ksw.mybudget.data.repository.spending.SpendingRepository
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import javax.inject.Inject

class GetAllSpendingUseCaseImpl @Inject constructor(
    private val spendingRepository: SpendingRepository
): GetAllSpendingUseCase {
    override suspend fun invoke(): Flow<List<SpendingItem>> {
        return spendingRepository.getAllSpendingEntities().map { list ->
            list.map {
                it.toItem()
            }
        }
    }
}