package kr.ksw.mybudget.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen() {
    val now = LocalDate
        .now()
        .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    val name = "김석우"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp),
    ) {
        HomeHeader(now, name)
    }
}

@Composable
private fun HomeHeader(
    now: String,
    name: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = now,
                fontSize = 20.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = String.format(
                    format = stringResource(R.string.main_home_header_name_title),
                    name
                ),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
        Icon(
            modifier = Modifier
                .size(36.dp),
            imageVector = Icons.Default.Add,
            contentDescription = "Add Spending Icon"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    MyBudgetTheme {
        HomeScreen()
    }
}

