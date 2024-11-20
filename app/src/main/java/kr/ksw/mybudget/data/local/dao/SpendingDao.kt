package kr.ksw.mybudget.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import java.util.Date

@Dao
interface SpendingDao {
    @Upsert
    suspend fun upsertSpendingEntity(spendingEntity: SpendingEntity)

    @Delete
    suspend fun deleteSpendingEntity(spendingEntity: SpendingEntity)

    @Query("SELECT * FROM spending_table")
    suspend fun getAllSpendingEntities(): Flow<List<SpendingEntity>>

    @Query("SELECT * FROM spending_table WHERE majorCategory = :majorCategory")
    suspend fun getSpendingEntitiesByMajorCategory(
        majorCategory: Int
    ): List<SpendingEntity>

    @Query("SELECT * FROM spending_table WHERE subCategory = :subCategory")
    suspend fun getSpendingEntitiesBySubCategory(
        subCategory: Int
    ): List<SpendingEntity>

    @Query("SELECT * FROM spending_table WHERE date BETWEEN :from AND :to")
    suspend fun getSpendingEntitiesBetween(
        from: Date,
        to: Date
    ): List<SpendingEntity>
}