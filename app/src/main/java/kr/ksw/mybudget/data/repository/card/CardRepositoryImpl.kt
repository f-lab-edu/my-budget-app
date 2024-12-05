package kr.ksw.mybudget.data.repository.card

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kr.ksw.mybudget.data.local.dao.CardDao
import kr.ksw.mybudget.data.local.entity.CardEntity
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
): CardRepository {
    override suspend fun upsertSpendingEntity(cardEntity: CardEntity) {
        cardDao.upsertSpendingEntity(cardEntity)
    }

    override suspend fun deleteSpendingEntity(cardEntity: CardEntity) {
        cardDao.deleteSpendingEntity(cardEntity)
    }

    override fun getAllCardEntities(): Flow<List<CardEntity>> = flow {
        cardDao.getAllCardEntities().collect {
            emit(it)
        }
    }.catch { emit(emptyList()) }
        .flowOn(Dispatchers.IO)
}