package kr.ksw.mybudget.domain.usecase.add

import kr.ksw.mybudget.data.repository.spending.SpendingRepository
import kr.ksw.mybudget.domain.mapper.toEntity
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import javax.inject.Inject

class AddSpendingUseCaseImpl @Inject constructor(
    private val spendingRepository: SpendingRepository
): AddSpendingUseCase {
    override suspend fun invoke(item: SpendingItem) {
        spendingRepository.upsertSpendingEntity(
            item.toEntity()
        )
    }
}