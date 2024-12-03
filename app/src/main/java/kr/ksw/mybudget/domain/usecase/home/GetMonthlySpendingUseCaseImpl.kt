package kr.ksw.mybudget.domain.usecase.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ksw.mybudget.data.local.dao.SpendingDao
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import java.time.LocalDate
import javax.inject.Inject

class GetMonthlySpendingUseCaseImpl @Inject constructor(
    private val spendingDao: SpendingDao
): GetMonthlySpendingUseCase {
    override suspend fun invoke(): Flow<List<SpendingItem>> {
        val now = LocalDate.now()
        val from = now.withDayOfMonth(1)
        val to = now.withDayOfMonth(now.lengthOfMonth())
        return spendingDao.getSpendingEntitiesBetweenFlow(from, to).map { list ->
            list.map {
                it.toItem()
            }
        }
    }
}