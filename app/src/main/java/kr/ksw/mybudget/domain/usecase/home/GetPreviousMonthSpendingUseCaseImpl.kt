package kr.ksw.mybudget.domain.usecase.home

import kr.ksw.mybudget.data.repository.spending.SpendingRepository
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import java.time.LocalDate
import javax.inject.Inject

class GetPreviousMonthSpendingUseCaseImpl @Inject constructor(
    private val spendingRepository: SpendingRepository
): GetPreviousMonthSpendingUseCase {
    override suspend fun invoke(): Result<List<SpendingItem>> = runCatching {
        val now = LocalDate.now().plusMonths(-1L)
        val from = now.withDayOfMonth(1)
        val to = now.withDayOfMonth(now.lengthOfMonth())
        spendingRepository.getSpendingEntitiesBetween(
            from, to
        ).map {
            it.toItem()
        }
    }
}