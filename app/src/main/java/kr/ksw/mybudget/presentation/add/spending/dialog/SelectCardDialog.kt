package kr.ksw.mybudget.presentation.add.spending.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.presentation.add.common.components.CardInfoText
import kr.ksw.mybudget.presentation.card.list.cardListStateMock
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

@Composable
fun SelectCardDialog(
    cardList: List<CardItem>,
    onCardSelect: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .sizeIn(maxHeight = 560.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.add_spending_card_dialog_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        LazyColumn(
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                count = cardList.size,
                key = {
                    it
                }
            ) { index ->
                val cardItem = cardList[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCardSelect(index)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                color = Color(cardItem.cardColor),
                            )
                    )
                    CardInfoText(cardItem = cardItem)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectCardDialogPreview() {
    MyBudgetTheme {
        Surface {
            SelectCardDialog(
                cardList = cardListStateMock.cardList
            ) {

            }
        }
    }
}