package kr.ksw.mybudget.domain.usecase.add.card

import kotlinx.coroutines.flow.Flow
import kr.ksw.mybudget.domain.model.card.CardItem

interface GetAllCardUseCase {
    fun invoke(): Flow<List<CardItem>>
}