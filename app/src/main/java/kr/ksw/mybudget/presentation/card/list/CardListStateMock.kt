package kr.ksw.mybudget.presentation.card.list

import kr.ksw.mybudget.data.local.entity.CARD_TYPE_CREDIT
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_DEBIT
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.domain.model.spending.SpendingItem
import kr.ksw.mybudget.domain.model.spending.SpendingType
import kr.ksw.mybudget.presentation.card.list.viewmodel.CardListState
import java.time.LocalDate

val cardListStateMock = CardListState(
    cardList = listOf(
        CardItem(
            id = 1,
            cardName = "SAMSUNG V2",
            cardNumber = "1234123412341234",
            cardColor = 0xFFD29CB4,
            cardType = CARD_TYPE_CREDIT
        ),
        CardItem(
            id = 2,
            cardName = "HYUNDAI M",
            cardNumber = "4567456745674567",
            cardColor = 0xFF34495e,
            cardType = CARD_TYPE_CREDIT
        ),
        CardItem(
            id = 3,
            cardName = "KAKAO CHECK",
            cardNumber = "7897987897897412",
            cardColor = 0xFF48c9b0,
            cardType = CARD_TYPE_DEBIT
        ),
    ),
    spendingList = listOf(
        SpendingItem(
            title = "스타벅스",
            date = LocalDate.now(),
            price = 6_000,
            category = SpendingType.CAFE,
            cardNum = "7897987897897412"
        ),
        SpendingItem(
            title = "할리스",
            date = LocalDate.now(),
            price = 5_000,
            category = SpendingType.CAFE,
            cardNum = "7897987897897412"
        ),
        SpendingItem(
            title = "영화",
            date = LocalDate.now(),
            price = 15_000,
            category = SpendingType.MOVIE,
            cardNum = "1234123412341234"
        ),
        SpendingItem(
            title = "교통비",
            date = LocalDate.now(),
            category = SpendingType.TRANSPORTATION,
            price = 100_000,
            cardNum = "4567456745674567"
        ),
        SpendingItem(
            title = "통신비",
            date = LocalDate.now(),
            price = 50_000,
            category = SpendingType.COMMUNICATION,
            cardNum = "4567456745674567"
        ),
        SpendingItem(
            title = "송금",
            date = LocalDate.now(),
            price = 200_000,
            category = SpendingType.TRANSFER,
            cardNum = "7897987897897412"
        ),
    )
)