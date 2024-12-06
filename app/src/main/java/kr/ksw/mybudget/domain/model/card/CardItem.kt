package kr.ksw.mybudget.domain.model.card

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardItem(
    val id: Int,
    val cardName: String,
    val cardNumber: String,
    val cardColor: Long,
    val cardType: Int,
): Parcelable
