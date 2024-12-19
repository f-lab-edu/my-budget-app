package kr.ksw.mybudget.presentation.card.list.screen

import android.content.Intent
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kr.ksw.mybudget.R
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.presentation.add.card.AddCardActivity
import kr.ksw.mybudget.presentation.card.list.cardListStateMock
import kr.ksw.mybudget.presentation.card.list.viewmodel.CardListActions
import kr.ksw.mybudget.presentation.card.list.viewmodel.CardListState
import kr.ksw.mybudget.presentation.card.list.viewmodel.CardListUIEffect
import kr.ksw.mybudget.presentation.card.list.viewmodel.CardListViewModel
import kr.ksw.mybudget.presentation.components.CardUi
import kr.ksw.mybudget.presentation.components.SpendingCard
import kr.ksw.mybudget.presentation.core.keys.CARD_ITEM_KEY
import kr.ksw.mybudget.ui.Paddings
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kotlin.math.absoluteValue

@Composable
fun CardListScreen(
    viewModel: CardListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.uiEffect) {
        viewModel.uiEffect.collectLatest { effect ->
            when(effect) {
                is CardListUIEffect.OpenAddCardScreen -> {
                    context.startActivity(Intent(
                        context,
                        AddCardActivity::class.java
                    ).apply {
                        putExtra(CARD_ITEM_KEY, effect.cardItem)
                    })
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    CardListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CardListScreen(
    state: CardListState,
    onAction: (CardListActions) -> Unit
) {
    val spendingList = state.spendingList.filter {
        val cardNum = if(state.cardList.isEmpty()) {
            ""
        } else {
            state.cardList[state.selectedCardIndex].cardNumber
        }
        it.cardNum == cardNum
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Paddings.ScaffoldTopPadding)
    ) {
        CardListHeader(
            onAction = onAction
        )
        Spacer(modifier = Modifier.height(20.dp))
        if(state.cardList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.card_list_empty_card_list),
                    fontSize = 20.sp
                )
            }
        } else {
            CardListPager(
                cardList = state.cardList,
                onAction = onAction
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.padding(
                    horizontal = Paddings.ScaffoldHorizontalPadding
                ),
                text = stringResource(R.string.main_home_spending_list_title),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            if(state.spendingList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.card_list_empty_spending_list_by_selected_card)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = Paddings.ScaffoldHorizontalPadding),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = spendingList.size,
                        key = {
                            it
                        }
                    ) { index ->
                        SpendingCard(
                            item = spendingList[index]
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CardListHeader(
    onAction: (CardListActions) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.ScaffoldHorizontalPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "카드 목록",
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onAction(CardListActions.OnClickAddIcon)
                },
            imageVector = Icons.Default.Add,
            contentDescription = "Add Card Icon"
        )
    }
}

@Composable
private fun CardListPager(
    cardList: List<CardItem>,
    onAction: (CardListActions) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        cardList.size
    })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onAction(CardListActions.OnSelectedCard(page))
        }
    }

    HorizontalPager(
        state = pagerState,
        key = { index ->
            cardList[index].cardNumber
        },
        contentPadding = PaddingValues(
            horizontal = 28.dp,
        ),
        pageSpacing = 10.dp
    ) { page ->
        val item = cardList[page]
        CardUi(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleY = lerp(
                        start = 1f,
                        stop = 0.8f,
                        fraction = pageOffset.absoluteValue.coerceIn(0f, 1f)
                    )
                }
                .clickable {
                    onAction(CardListActions.OnClickCardItem(item))
                },
            cardName = item.cardName,
            cardNumber = item.cardNumber,
            cardType = item.cardType,
            cardColor = item.cardColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardListScreenPreview() {
    MyBudgetTheme {
        CardListScreen(
            state = cardListStateMock
        ) {

        }
    }
}