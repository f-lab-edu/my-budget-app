package kr.ksw.mybudget.data.repository.spending

import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import java.time.LocalDate

interface SpendingRepository {
    suspend fun upsertSpendingEntity(spendingEntity: SpendingEntity)

    suspend fun deleteSpendingEntity(spendingEntity: SpendingEntity)

    fun getAllSpendingEntities(): Flow<List<SpendingEntity>>

    suspend fun getSpendingEntitiesByMajorCategory(
        majorCategory: Int
    ): List<SpendingEntity>

    suspend fun getSpendingEntitiesBySubCategory(
        subCategory: Int
    ): List<SpendingEntity>

    suspend fun getSpendingEntitiesBetween(
        from: LocalDate,
        to: LocalDate
    ): List<SpendingEntity>
}