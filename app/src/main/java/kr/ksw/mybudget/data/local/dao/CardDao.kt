package kr.ksw.mybudget.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.data.local.entity.CardEntity

@Dao
interface CardDao {
    @Upsert
    suspend fun upsertSpendingEntity(cardEntity: CardEntity)

    @Delete
    suspend fun deleteSpendingEntity(cardEntity: CardEntity)

    @Query("SELECT * FROM card_table")
    fun getAllCardEntities(): Flow<List<CardEntity>>
}