package kr.ksw.mybudget.data.repository.spending

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.ksw.mybudget.data.local.dao.SpendingDao
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import java.time.LocalDate
import javax.inject.Inject

class SpendingRepositoryImpl @Inject constructor(
    private val spendingDao: SpendingDao
): SpendingRepository {
    override suspend fun upsertSpendingEntity(spendingEntity: SpendingEntity) {
        spendingDao.upsertSpendingEntity(spendingEntity)
    }

    override suspend fun deleteSpendingEntity(spendingEntity: SpendingEntity) {
        spendingDao.deleteSpendingEntity(spendingEntity)
    }

    override fun getAllSpendingEntities(): Flow<List<SpendingEntity>> = flow {
        spendingDao.getAllSpendingEntities().collect {
            emit(it)
        }
    }.catch { emit(emptyList()) }
        .flowOn(Dispatchers.IO)

    override fun getSpendingEntitiesBetween(
        from: LocalDate,
        to: LocalDate
    ): Flow<List<SpendingEntity>> = flow {
        spendingDao.getSpendingEntitiesBetween(from, to).collect {
            emit(it)
        }
    }.catch { emit(emptyList()) }
        .flowOn(Dispatchers.IO)

    override suspend fun getSpendingEntitiesByMajorCategory(majorCategory: Int): List<SpendingEntity>
        = spendingDao.getSpendingEntitiesByMajorCategory(majorCategory = majorCategory)

    override suspend fun getSpendingEntitiesBySubCategory(subCategory: Int): List<SpendingEntity>
        = spendingDao.getSpendingEntitiesBySubCategory(subCategory = subCategory)
}