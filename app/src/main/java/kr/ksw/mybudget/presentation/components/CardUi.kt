package kr.ksw.mybudget.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_CREDIT
import kr.ksw.mybudget.presentation.core.common.getCardColor
import kr.ksw.mybudget.presentation.core.common.setSaturation
import kr.ksw.mybudget.ui.theme.MyBudgetTheme

const val bankCardAspectRatio = 1.586F // (width:height = 85.60mm:53.98mm)

@Composable
fun CardUi(
    modifier: Modifier = Modifier,
    cardName: String,
    cardNumber: String,
    cardColor: Long,
    cardType: Int = CARD_TYPE_CREDIT
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(bankCardAspectRatio),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box {
            CardUiBackground(baseColor = Color(cardColor))  // Random Hex Color
            CardHeader(
                cardName = cardName,
                cardType = cardType
            )
            CardNumber(
                modifier = Modifier
                    .align(Alignment.Center),
                cardNumber = cardNumber
            )
        }
    }
}

@Composable
private fun CardUiBackground(baseColor: Color) {
    val colorSaturation75 = baseColor.setSaturation(0.75f)
    val colorSaturation50 = baseColor.setSaturation(0.5f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(baseColor)
    ) {
        drawCircle(
            color = colorSaturation75,
            center = Offset(x = size.width * 0.2f, y = size.height * 0.6f),
            radius = size.minDimension * 0.85f
        )
        drawCircle(
            color = colorSaturation50,
            center = Offset(x = size.width * 0.1f, y = size.height * 0.3f),
            radius = size.minDimension * 0.75f
        )
    }
}

@Composable
private fun CardHeader(
    cardName: String,
    cardType: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = cardName,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = stringResource(
                if(cardType == CARD_TYPE_CREDIT) {
                    R.string.card_type_credit
                } else {
                    R.string.card_type_debit
                }
            ),
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
private fun CardNumber(
    modifier: Modifier = Modifier,
    cardNumber: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) {
            CardDotGroup()
        }

        if(cardNumber.length >= 16) {
            Text(
                text = cardNumber.takeLast(4),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                )
            )
        } else {
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}

@Composable
private fun CardDotGroup() {
    Canvas(
        modifier = Modifier.width(48.dp),
        onDraw = { // You can adjust the width as needed
            val dotRadius = 4.dp.toPx()
            val spaceBetweenDots = 8.dp.toPx()
            for (i in 0 until 4) { // Draw four dots
                drawCircle(
                    color = Color.White,
                    radius = dotRadius,
                    center = Offset(
                        x = i * (dotRadius * 2 + spaceBetweenDots) + dotRadius,
                        y = center.y
                    )
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun CardUiPreview() {
    MyBudgetTheme {
        Surface {
            CardUi(
                cardName = "SAMSUNG V2",
                cardNumber = "1234567890123456",
                cardColor = getCardColor()
            )
        }
    }
}