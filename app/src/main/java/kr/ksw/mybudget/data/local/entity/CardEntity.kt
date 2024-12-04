package kr.ksw.mybudget.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val cardName: String,
    val cardNumber: String,
    val cardType: Int,
)

const val CARD_TYPE_CREDIT = 1
const val CARD_TYPE_DEBIT = 2
