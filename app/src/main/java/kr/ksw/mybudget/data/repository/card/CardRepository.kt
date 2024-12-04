package kr.ksw.mybudget.data.repository.card

import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.data.local.entity.CardEntity

interface CardRepository {
    suspend fun upsertSpendingEntity(cardEntity: CardEntity)

    suspend fun deleteSpendingEntity(cardEntity: CardEntity)

    fun getAllCardEntities(): Flow<List<CardEntity>>
}