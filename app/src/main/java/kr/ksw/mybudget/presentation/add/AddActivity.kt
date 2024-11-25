package kr.ksw.mybudget.presentation.add

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import kr.ksw.mybudget.presentation.add.screen.AddSpendingScreen
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

class AddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBudgetTheme {
                Surface {
                    AddSpendingScreen()
                }
            }
        }
    }
}