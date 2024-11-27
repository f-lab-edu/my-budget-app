package kr.ksw.mybudget.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.ksw.mybudget.R

enum class SpendingType(
    val majorCategory: Int,
    val subCategory: Int,
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
) {
    // Food Category
    RESTAURANT(1, 11, R.drawable.ic_restaurant_24_outlined, R.string.spending_category_restaurant),
    CAFE(1, 12, R.drawable.ic_cafe_24_outlined, R.string.spending_category_cafe),
    DELIVERY(1, 13, R.drawable.ic_delivery_24_outlined, R.string.spending_category_delivery),
    GROCERY(1, 14, R.drawable.ic_grocery_24_outlined, R.string.spending_category_grocery),

    // Culture Category
    SUBSCRIPTION(2, 21, R.drawable.ic_subscription_24_outlined, R.string.spending_category_subscription),
    MOVIE(2, 22, R.drawable.ic_movie_24_outlined, R.string.spending_category_movie),
    BOOK(2, 23, R.drawable.ic_book_24_outlined, R.string.spending_category_performance),
    PERFORMANCE(2, 24, R.drawable.ic_performance_24_outlined, R.string.spending_category_book),

    TRANSPORTATION(3, 31, R.drawable.ic_bus_24_outlined, R.string.spending_category_transportation),
    COMMUNICATION(3, 32, R.drawable.ic_call_24_outlined, R.string.spending_category_communication),
    HOUSING(3, 33, R.drawable.ic_apartment_24_outlined, R.string.spending_category_housing),
    HOSPITAL(3, 34, R.drawable.ic_stethoscope_24_outlined, R.string.spending_category_hospital),
    MEDICINE(3, 35, R.drawable.ic_medication_24_outlined, R.string.spending_category_medicine),
    APPAREL(3, 36, R.drawable.ic_apparel_24_outlined, R.string.spending_category_apparel),
    EDUCATION(3, 37, R.drawable.ic_school_24_outlined, R.string.spending_category_education),

    TRANSFER(4, 41, R.drawable.ic_send_money_24_outlined, R.string.spending_category_transfer),

    UNKNOWN(-1, -1, R.drawable.ic_receipt_long_24_outlined, R.string.spending_category_etc)
}