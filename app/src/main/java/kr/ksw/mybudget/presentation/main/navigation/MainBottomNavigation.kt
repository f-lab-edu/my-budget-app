package kr.ksw.mybudget.presentation.main.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kr.ksw.mybudget.presentation.main.navigation.route.MainRoute
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

@Composable
fun MainBottomNavigation(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: MainRoute = navBackStackEntry?.destination?.route?.let {
        MainRoute.entries.find { route -> it == route.route }
    } ?: MainRoute.HOME

    MainBottomNavigation(
        currentRoute = currentRoute
    ) { newRoute ->
        navController.navigate(route = newRoute.route) {
            navController.graph.startDestinationRoute?.let { startRoute ->
                popUpTo(startRoute) {
                    saveState = true
                }
            }
            this.launchSingleTop = true
            this.restoreState = true
        }
    }
}

@Composable
fun MainBottomNavigation(
    currentRoute: MainRoute,
    onTabClick: (MainRoute) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MainRoute.entries.forEach {
            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(vertical = 4.dp)
                    .clickable {
                        onTabClick(it)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = if(currentRoute == it) {
                        ImageVector.vectorResource(it.selectedIcon)
                    } else {
                        ImageVector.vectorResource(it.unSelectedIcon)
                    },
                    contentDescription = stringResource(it.title),
                    tint = getTintColor(currentRoute == it)
                )
                Text(
                    text = stringResource(it.title),
                    fontSize = 12.sp,
                    color = getTintColor(currentRoute == it)
                )
            }
        }
    }
}

@Composable
private fun getTintColor(selected: Boolean): Color = if(selected) {
    MaterialTheme.colorScheme.primary
} else {
    Color.LightGray
}

@Preview
@Composable
fun MainBottomNavigationPreview() {
    MyBudgetTheme {
        Surface {
            var currentRoute by remember {
                mutableStateOf(MainRoute.HOME)
            }
            MainBottomNavigation(
                currentRoute = currentRoute
            ) { route ->
                currentRoute = route
            }
        }
    }
}