package kr.ksw.mybudget.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.presentation.core.common.DATE_FORMAT_YMD
import kr.ksw.mybudget.presentation.core.common.toDisplayString
import kr.ksw.mybudget.presentation.core.common.toPriceString
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.redTextColor
import kr.ksw.mybudget.ui.theme.turquoiseIconBgColor
import kr.ksw.mybudget.ui.theme.turquoiseIconColor
import java.time.LocalDate

@Composable
fun SpendingCard(
    item: SpendingItem,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = turquoiseIconBgColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(26.dp),
                    imageVector = ImageVector.vectorResource(
                        item.category.iconId
                    ),
                    contentDescription = "",
                    tint = turquoiseIconColor
                )
            }
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Column(
                modifier = Modifier.weight(1F)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = item.date.toDisplayString(DATE_FORMAT_YMD),
                    fontSize = 14.sp,
                    color = Color.LightGray
                )
            }
            Text(
                modifier = Modifier.weight(1F),
                text = "-${String.format(
                    stringResource(R.string.display_currency_won),
                    item.price.toPriceString()
                )}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = redTextColor,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SpendingCardPreview() {
    MyBudgetTheme {
        SpendingCard(
            item = SpendingEntity(
                title = "스타벅스",
                date = LocalDate.now(),
                majorCategory = 1,
                subCategory = 12,
                price = 6_000
            ).toItem()
        ) {

        }
    }
}