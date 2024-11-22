package kr.ksw.mybudget.presentation.main.navigation.route

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.ksw.mybudget.R

enum class MainRoute(
    val route: String,
    @DrawableRes val unSelectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes val title: Int,
) {
    HOME(
        route = "Home",
        unSelectedIcon = R.drawable.ic_home_24_outlined,
        selectedIcon = R.drawable.ic_home_24_filled,
        title = R.string.main_route_name_home
    ),
    STATISTICS(
        route = "Statistics",
        unSelectedIcon = R.drawable.ic_finance_24,
        selectedIcon = R.drawable.ic_finance_24,
        title = R.string.main_route_name_statistics
    ),
    BUDGET(
        route = "Budget",
        unSelectedIcon = R.drawable.ic_savings_24_outlined,
        selectedIcon = R.drawable.ic_savings_24_filled,
        title = R.string.main_route_name_budget
    ),
    CARDS(
        route = "Cards",
        unSelectedIcon = R.drawable.ic_credit_card_24_outlined,
        selectedIcon = R.drawable.ic_credit_card_24_filled,
        title = R.string.main_route_name_card
    )
}