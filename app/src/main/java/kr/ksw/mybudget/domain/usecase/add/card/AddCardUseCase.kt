package kr.ksw.mybudget.domain.usecase.add.card

import kr.ksw.mybudget.domain.model.card.CardItem

interface AddCardUseCase {
    suspend operator fun invoke(cardItem: CardItem)
}