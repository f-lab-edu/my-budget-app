package kr.ksw.mybudget.presentation.add.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.presentation.common.DATE_FORMAT_YMD_ADD
import kr.ksw.mybudget.presentation.common.toDisplayString
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.outlineTextFieldBorder
import kr.ksw.mybudget.ui.theme.turquoise
import java.time.LocalDate

@Composable
fun AddSpendingScreen(
    item: SpendingItem
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 42.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.add_spending_screen_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        AddSpendingView(
            title = stringResource(R.string.add_spending_screen_title_date),
            contentText = item.date.toDisplayString(DATE_FORMAT_YMD_ADD),
            iconImage = Icons.Default.DateRange,
            contentDescription = "Select Date",
        ) {
            // open date picker
        }
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingView(
            title = stringResource(R.string.add_spending_screen_title_category),
            contentText = stringResource(item.category.titleId),
            iconImage = ImageVector.vectorResource(
                item.category.iconId
            ),
            contentDescription = "Select Category",
        ) {
            // open category dialog
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = turquoise
            ),
            onClick = {

            }
        ) {
            Text(
                text = "추가하기",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun AddSpendingView(
    title: String,
    contentText: String,
    iconImage: ImageVector,
    contentDescription: String,
    onRowClick: () -> Unit
) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = outlineTextFieldBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onRowClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp),
            imageVector = iconImage,
            contentDescription = contentDescription,
            tint = inputTextColor
        )
        Text(
            text = contentText,
            color = inputTextColor,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun AddSpendingInputText() {

}

@Preview(showBackground = true)
@Composable
fun AddSpendingScreenPreview() {
    MyBudgetTheme {
        AddSpendingScreen(
            item = SpendingEntity(
                title = "영화",
                date = LocalDate.now(),
                majorCategory = 2,
                subCategory = 22,
                price = 15_000
            ).toItem()
        )
    }
}