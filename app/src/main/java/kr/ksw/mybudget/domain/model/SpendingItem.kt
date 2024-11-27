package kr.ksw.mybudget.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class SpendingItem(
    val id: Int? = null,
    val title: String = "",
    val date: LocalDate = LocalDate.now(),
    val price: Int = 0,
    val category: SpendingType = SpendingType.UNKNOWN,
    val cardNum: String? = null,
) : Parcelable