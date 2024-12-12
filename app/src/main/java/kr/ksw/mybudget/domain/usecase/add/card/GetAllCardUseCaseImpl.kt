package kr.ksw.mybudget.domain.usecase.add.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.ksw.mybudget.data.repository.card.CardRepository
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.card.CardItem
import javax.inject.Inject

class GetAllCardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository
) : GetAllCardUseCase {
    override fun invoke(): Flow<List<CardItem>> = cardRepository.getAllCardEntities().map { list ->
        list.map {
            it.toItem()
        }
    }
}