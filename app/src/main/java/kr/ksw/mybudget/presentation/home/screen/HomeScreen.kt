package kr.ksw.mybudget.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.turquoise
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
            .padding(top = 36.dp)
            .padding(horizontal = 16.dp),
    ) {
        HomeHeader(now, name)
        Spacer(modifier = Modifier.height(16.dp))
        HomeSpendingCard()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "지출내역",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.padding(top = 6.dp)
        ) {
            Text(
                text = now,
                fontSize = 18.sp,
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
                .size(32.dp)
                .clickable {

                },
            imageVector = Icons.Default.Add,
            contentDescription = "Add Spending Icon"
        )
    }
}

@Composable
private fun HomeSpendingCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(turquoise)
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
        ) {
            Text(
                text = stringResource(R.string.main_home_spending_card_title),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                text = String.format(
                    stringResource(R.string.display_currency_won),
                    100_000
                ),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.End),
                horizontalAlignment = Alignment.End
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.main_home_spending_card_compare_title),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = String.format(
                        stringResource(R.string.display_currency_won),
                        50_000
                    ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    MyBudgetTheme {
        HomeScreen()
    }
}

