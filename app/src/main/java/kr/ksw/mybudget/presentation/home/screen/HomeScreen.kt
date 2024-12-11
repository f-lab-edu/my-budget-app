package kr.ksw.mybudget.presentation.home.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.ksw.mybudget.R
import kr.ksw.mybudget.presentation.add.spending.AddSpendingActivity
import kr.ksw.mybudget.presentation.core.common.DATE_FORMAT_YMD
import kr.ksw.mybudget.presentation.core.common.toDisplayString
import kr.ksw.mybudget.presentation.core.common.toPriceString
import kr.ksw.mybudget.presentation.components.SpendingCard
import kr.ksw.mybudget.presentation.core.common.Paddings
import kr.ksw.mybudget.presentation.core.keys.SPENDING_ITEM_KEY
import kr.ksw.mybudget.presentation.home.viewmodel.HomeState
import kr.ksw.mybudget.presentation.home.viewmodel.HomeViewModel
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.turquoise
import java.time.LocalDate
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeScreen(state)
}

@Composable
fun HomeScreen(
    state: HomeState
) {
    val now = LocalDate.now().toDisplayString(DATE_FORMAT_YMD)
    val name = "김석우"

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Paddings.ScaffoldTopPadding)
            .padding(horizontal = Paddings.ScaffoldHorizontalPadding),
    ) {
        HomeHeader(context, now, name)
        Spacer(modifier = Modifier.height(16.dp))
        HomeSpendingCard(
            totalSpend = state.spendingList.sumOf {
                it.price
            },
            lastSpend = state.lastSpend
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(state.spendingList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "첫 지출 내역을 입력해 보세요!",
                    fontSize = 14.sp,
                    color = inputTextColor
                )
            }
        } else {
            Text(
                text = stringResource(R.string.main_home_spending_list_title),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyColumn(
                contentPadding = PaddingValues(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    count = state.spendingList.size,
                    key = {
                        it
                    }
                ) { index ->
                    SpendingCard(
                        item = state.spendingList[index]
                    ) {
                        context.startActivity(
                            Intent(context, AddSpendingActivity::class.java).apply {
                                putExtra(SPENDING_ITEM_KEY, state.spendingList[index])
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeader(
    context: Context,
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
                    context.startActivity(
                        Intent(context, AddSpendingActivity::class.java)
                    )
                },
            imageVector = Icons.Default.Add,
            contentDescription = "Add Spending Icon"
        )
    }
}

@Composable
private fun HomeSpendingCard(
    totalSpend: Int,
    lastSpend: Int,
) {
    val mom = lastSpend - totalSpend
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
                    totalSpend.toPriceString()
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
                        modifier = Modifier
                            .rotate(if(mom > 0) {
                                0F
                            } else {
                                180F
                            }),
                        imageVector = if(mom > 0) {
                            Icons.Default.ArrowDropDown
                        } else {
                            Icons.Default.ArrowDropDown
                        },
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = String.format(
                        stringResource(R.string.display_currency_won),
                        mom.absoluteValue.toPriceString()
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
        HomeScreen(
            state = HomeState(
                spendingList = emptyList()
            )
        )
    }
}

