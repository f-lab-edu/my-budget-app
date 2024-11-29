package kr.ksw.mybudget.presentation.main.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kr.ksw.mybudget.presentation.main.navigation.route.MainRoute

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost (
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute.HOME.route
    ) {
        composable(route = MainRoute.HOME.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(MainRoute.HOME.title)
                )
            }
        }
        composable(route = MainRoute.STATISTICS.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(MainRoute.STATISTICS.title)
                )
            }
        }
        composable(route = MainRoute.BUDGET.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(MainRoute.BUDGET.title)
                )
            }
        }
        composable(route = MainRoute.CARDS.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(MainRoute.CARDS.title)
                )
            }
        }
    }
}