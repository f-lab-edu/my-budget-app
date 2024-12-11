package kr.ksw.mybudget.presentation.add.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.ksw.mybudget.presentation.add.card.screen.AddCardScreen
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

@AndroidEntryPoint
class AddCardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBudgetTheme {
                AddCardScreen()
            }
        }
    }
}