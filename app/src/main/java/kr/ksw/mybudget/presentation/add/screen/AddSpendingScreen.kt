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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.text.isDigitsOnly
import kotlinx.coroutines.flow.collectLatest
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.SpendingEntity
import kr.ksw.mybudget.domain.mapper.toItem
import kr.ksw.mybudget.domain.model.SpendingType
import kr.ksw.mybudget.presentation.add.dialog.SelectCategoryDialog
import kr.ksw.mybudget.presentation.add.transformation.NumberCommaTransformation
import kr.ksw.mybudget.presentation.add.viewmodel.AddSpendingActions
import kr.ksw.mybudget.presentation.add.viewmodel.AddSpendingState
import kr.ksw.mybudget.presentation.add.viewmodel.AddSpendingUIEffect
import kr.ksw.mybudget.presentation.add.viewmodel.AddSpendingViewModel
import kr.ksw.mybudget.presentation.core.common.DATE_FORMAT_YMD_ADD
import kr.ksw.mybudget.presentation.core.common.toDisplayString
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputHintTextColor
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.outlineTextFieldBorder
import kr.ksw.mybudget.ui.theme.turquoise
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Date
import java.util.Locale

@Composable
fun AddSpendingScreen(
    viewModel: AddSpendingViewModel,
    onFinish: () -> Unit
) {
    LaunchedEffect(viewModel.uiEffect) {
        viewModel.uiEffect.collectLatest { effect ->
            when(effect) {
                is AddSpendingUIEffect.FinishAddScreen -> {
                    onFinish()
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    AddSpendingScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AddSpendingScreen(
    state: AddSpendingState,
    onAction: (AddSpendingActions) -> Unit
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
            contentText = state.item.date.toDisplayString(DATE_FORMAT_YMD_ADD),
            iconImage = Icons.Default.DateRange,
            contentDescription = "Select Date",
        ) {
            onAction(AddSpendingActions.OnClickDateRow)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingView(
            title = stringResource(R.string.add_spending_screen_title_category),
            contentText = stringResource(state.item.category.titleId),
            iconImage = ImageVector.vectorResource(
                state.item.category.iconId
            ),
            contentDescription = "Select Category",
        ) {
            onAction(AddSpendingActions.OnClickCategoryRow)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingInputForm(
            title = stringResource(R.string.add_spending_screen_title_name),
            text = state.item.title,
            placeHolder = stringResource(R.string.add_spending_screen_name_hint),
            onTextChange = { text ->
                onAction(AddSpendingActions.OnTitleChanged(text))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddSpendingInputForm(
            title = stringResource(R.string.add_spending_screen_title_price),
            text = state.item.price.toString(),
            placeHolder = stringResource(R.string.add_spending_screen_price_hint),
            onTextChange = { text ->
                onAction(AddSpendingActions.OnPriceChanged(text))
            },
            keyboardType = KeyboardType.Decimal,
            visualTransformation = NumberCommaTransformation()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 20.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = turquoise
            ),
            onClick = {
                onAction(AddSpendingActions.OnClickAddButton)
            }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(R.string.add_spending_screen_add_button),
                fontSize = 18.sp
            )
        }
    }

    if(state.showDatePickerDialog) {
        DatePickerModal(
            date = state.item.date,
        ) { date ->
            onAction(AddSpendingActions.OnDismissDateRow(
                date = date
            ))
        }
    }

    if(state.showCategoryDialog) {
        Dialog(
            onDismissRequest = {
                onAction(AddSpendingActions.OnDismissCategoryRow())
            }
        ) {
            SelectCategoryDialog { categoryIndex ->
                onAction(AddSpendingActions.OnDismissCategoryRow(
                    category = SpendingType.entries[categoryIndex]
                ))
            }
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
        )
    }
}

@Composable
private fun AddSpendingInputForm(
    title: String,
    text: String,
    placeHolder: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
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
        value = if(text == "0") {
            ""
        } else {
            text
        },
        onValueChange = {
            if(keyboardType == KeyboardType.Decimal) {
                if (it.length < 10 && it.isDigitsOnly()) {
                    onTextChange(it)
                }
            } else {
                onTextChange(it)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    date: LocalDate? = LocalDate.now(),
    onDismiss: (String?) -> Unit
) {
    val initialDateMillis = date?.atStartOfDay(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
        ?: System.currentTimeMillis()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis
    )

    DatePickerDialog(
        modifier = Modifier.scale(0.8f),
        onDismissRequest = {
            onDismiss(null)
        },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                    val formatter = SimpleDateFormat(DATE_FORMAT_YMD_ADD, Locale.getDefault())
                    val formattedDate = formatter.format(Date(selectedDateMillis))
                    formattedDate
                }
                onDismiss(selectedDate)
            }) {
                Text(stringResource(R.string.common_button_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss(null)
            }) {
                Text(stringResource(R.string.common_button_cancel))
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddSpendingScreenPreview() {
    MyBudgetTheme {
        AddSpendingScreen(
            state = AddSpendingState(
                item = SpendingEntity(
                    title = "영화",
                    date = LocalDate.now(),
                    majorCategory = 2,
                    subCategory = 22,
                    price = 15_000
                ).toItem()
            )
        ) { }
    }
}