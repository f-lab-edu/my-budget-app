package kr.ksw.mybudget.domain.usecase.add.card

import kr.ksw.mybudget.data.repository.card.CardRepository
import kr.ksw.mybudget.domain.mapper.toEntity
import kr.ksw.mybudget.domain.model.card.CardItem
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(
    private val cardRepository: CardRepository
): AddCardUseCase {
    override suspend fun invoke(cardItem: CardItem) {
        cardRepository.upsertSpendingEntity(cardItem.toEntity())
    }
}