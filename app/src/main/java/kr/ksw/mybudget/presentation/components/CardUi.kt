package kr.ksw.mybudget.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

const val bankCardAspectRatio = 1.586F // (width:height = 85.60mm:53.98mm)

@Composable
fun CardUi() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(bankCardAspectRatio),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Box {
            BankCardBackground(baseColor = Color(0xFF1252c8))
        }
    }
}

@Composable
private fun BankCardBackground(baseColor: Color) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(baseColor)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun CardUiPreview() {
    MyBudgetTheme {
        Surface {
            CardUi()
        }
    }
}