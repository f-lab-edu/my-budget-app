package kr.ksw.mybudget.presentation.add.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingItem
import kr.ksw.mybudget.presentation.add.transformation.NumberCommaTransformation
import kr.ksw.mybudget.presentation.core.common.DATE_FORMAT_YMD_ADD
import kr.ksw.mybudget.presentation.core.common.toDisplayString
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputHintTextColor
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.outlineTextFieldBorder
import kr.ksw.mybudget.ui.theme.turquoise
import java.time.LocalDate

@Composable
fun AddSpendingScreen(
    item: SpendingItem
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 42.dp)
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
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
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingInputForm(
            title = "지출 이름",
            text = item.title,
            placeHolder = "지출 내역을 입력해 주세요."
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingInputForm(
            title = "지출 비용",
            text = item.price.toString(),
            placeHolder = "비용을 입력해 주세요.",
            keyboardType = KeyboardType.Decimal,
            visualTransformation = NumberCommaTransformation()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 20.dp)
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
private fun AddSpendingInputForm(
    title: String,
    text: String,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var inputText by remember { mutableStateOf(text) }

    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        value = inputText,
        onValueChange = {
            if(keyboardType == KeyboardType.Decimal) {
                if (it.length < 10 && it.isDigitsOnly()) {
                    inputText = it
                }
            } else {
                inputText = it
            }
        },
        textStyle = LocalTextStyle.current.copy(
            color = inputTextColor
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                text = placeHolder,
                color = inputHintTextColor
            )
        },
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedIndicatorColor = turquoise,
            unfocusedIndicatorColor = outlineTextFieldBorder
        ),
        visualTransformation = visualTransformation
    )
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