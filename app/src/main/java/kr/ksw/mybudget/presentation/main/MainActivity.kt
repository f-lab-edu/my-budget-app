package kr.ksw.mybudget.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ksw.mybudget.presentation.main.navigation.MainBottomNavigation
import kr.ksw.mybudget.presentation.main.navigation.MainNavHost
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBudgetTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomNavigation(navController)
                    }
                ) { innerPadding ->
                    Surface {
                        MainNavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}