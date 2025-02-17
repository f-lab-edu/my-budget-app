package kr.ksw.mybudget.domain.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_CREDIT
import kr.ksw.mybudget.presentation.core.common.getCardColor

@Parcelize
data class CardItem(
    val id: Int = -1,
    val cardName: String = "",
    val cardNumber: String = "",
    val cardColor: Long = getCardColor(),
    val cardType: Int = CARD_TYPE_CREDIT,
): Parcelable
