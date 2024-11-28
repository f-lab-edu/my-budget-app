package kr.ksw.mybudget.presentation.add

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.presentation.add.screen.AddSpendingScreen
import kr.ksw.mybudget.presentation.core.keys.SPENDING_ITEM_KEY
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

@AndroidEntryPoint
class AddActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = intent.getParcelableExtra<SpendingItem>(SPENDING_ITEM_KEY)
        setContent {
            MyBudgetTheme {
                Surface {
                    AddSpendingScreen()
                }
            }
        }
    }
}