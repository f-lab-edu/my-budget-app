package kr.ksw.mybudget.domain.model

import androidx.annotation.DrawableRes
import kr.ksw.mybudget.R

enum class SpendingType(
    val majorCategory: Int,
    val subCategory: Int,
    @DrawableRes val iconId: Int,
) {
    // Food Category
    RESTAURANT(1, 11, R.drawable.ic_restaurant_24_outlined),
    CAFE(1, 12, R.drawable.ic_cafe_24_outlined),
    DELIVERY(1, 13, R.drawable.ic_delivery_24_outlined),
    GROCERY(1, 14, R.drawable.ic_grocery_24_outlined),

    // Culture Category
    SUBSCRIPTION(2, 21, R.drawable.ic_subscription_24_outlined),
    MOVIE(2, 22, R.drawable.ic_movie_24_outlined),
    BOOK(2, 23, R.drawable.ic_book_24_outlined),
    PERFORMANCE(2, 24, R.drawable.ic_performance_24_outlined),

    TRANSPORTATION(3, 31, R.drawable.ic_bus_24_outlined),
    COMMUNICATION(3, 32, R.drawable.ic_call_24_outlined),
    HOUSING(3, 33, R.drawable.ic_apartment_24_outlined),
    HOSPITAL(3, 34, R.drawable.ic_stethoscope_24_outlined),
    MEDICINE(3, 35, R.drawable.ic_medication_24_outlined),
    APPAREL(3, 36, R.drawable.ic_apparel_24_outlined),
    EDUCATION(3, 37, R.drawable.ic_school_24_outlined),

    TRANSFER(4, 41, R.drawable.ic_send_money_24_outlined),

    UNKNOWN(-1, -1, R.drawable.ic_receipt_long_24_outlined)
}