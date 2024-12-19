package kr.ksw.mybudget.presentation.add.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import kr.ksw.mybudget.domain.model.card.CardItem
import kr.ksw.mybudget.ui.theme.inputTextColor

@Composable
fun CardInfoText(
    cardItem: CardItem,
    fontColor: Color = inputTextColor,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = spendingCardText(cardItem),
        color = fontColor,
        fontSize = fontSize,
        fontWeight = fontWeight,
    )
}

private fun spendingCardText(
    cardItem: CardItem
): String = "${cardItem.cardName.let {
    if(it.length > 7) {
        it.take(7)
    } else {
        it
    }
}}, **** **** **** ${cardItem.cardNumber.takeLast(4)}"