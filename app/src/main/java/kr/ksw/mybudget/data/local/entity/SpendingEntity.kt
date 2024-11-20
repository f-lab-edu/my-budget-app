package kr.ksw.mybudget.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("spending_table")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val date: Date,
    val price: Int,
    val majorCategory: Int,
    val subCategory: Int,
    val cardNum: String? = null,
)