package kr.ksw.mybudget.presentation.add.card.screen

import android.content.Context
import android.graphics.Rect
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kr.ksw.mybudget.R
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_CREDIT
import kr.ksw.mybudget.data.local.entity.CARD_TYPE_DEBIT
import kr.ksw.mybudget.presentation.add.card.viewmodel.AddCardActions
import kr.ksw.mybudget.presentation.add.card.viewmodel.AddCardState
import kr.ksw.mybudget.presentation.add.card.viewmodel.AddCardUIEffect
import kr.ksw.mybudget.presentation.add.card.viewmodel.AddCardViewModel
import kr.ksw.mybudget.presentation.add.common.components.ADD_INPUT_TYPE_CARD
import kr.ksw.mybudget.presentation.add.common.components.AddInputForm
import kr.ksw.mybudget.presentation.add.common.transformation.CardNumberTransformation
import kr.ksw.mybudget.presentation.components.CardUi
import kr.ksw.mybudget.ui.Paddings
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.outlineTextFieldBorder
import kr.ksw.mybudget.ui.theme.turquoise
import kr.ksw.mybudget.ui.theme.turquoiseTextColor

private const val VERTICAL_SCROLL_RANGE = 2000
private const val VERTICAL_SCROLL_ANIMATION_DURATION = 500

@Composable
fun AddCardScreen(
    viewModel: AddCardViewModel = hiltViewModel(),
    onFinish: () -> Unit = {}
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.uiEffect) {
        viewModel.uiEffect.collectLatest { effect ->
            when(effect) {
                is AddCardUIEffect.FinishAddScreen -> {
                    showToast(
                        context = context,
                        message = effect.message
                    )
                    onFinish()
                }
                is AddCardUIEffect.ShowToastMessage -> {
                    showToast(
                        context = context,
                        message = effect.message
                    )
                }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    AddCardScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AddCardScreen(
    state: AddCardState,
    onAction: (AddCardActions) -> Unit
) {
    val scrollState = rememberScrollState()
    val isKeyboardVisible by keyboardVisibility()
    LaunchedEffect(key1 = isKeyboardVisible) {
        if(isKeyboardVisible) {
            scrollState.animateScrollTo(
                value = VERTICAL_SCROLL_RANGE,
                animationSpec = tween(
                    durationMillis = VERTICAL_SCROLL_ANIMATION_DURATION,
                    easing = LinearEasing
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Paddings.ScaffoldTopPadding)
            .padding(horizontal = Paddings.ScaffoldHorizontalPadding)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(R.string.add_card_screen_title),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(20.dp))
        CardUi(
            modifier = Modifier
                .padding(
                    horizontal = Paddings.CardHorizontalPadding,
                ),
            cardName = state.cardItem.cardName,
            cardNumber = state.cardItem.cardNumber,
            cardColor = state.cardItem.cardColor,
            cardType = state.cardItem.cardType
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddInputForm(
            title = stringResource(R.string.add_card_screen_name_title),
            text = state.cardItem.cardName,
            placeHolder = stringResource(R.string.add_card_screen_name_hint),
            onTextChange = {
                onAction(AddCardActions.UpdateCardName(it))
            },
            maxLength = 15
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddInputForm(
            title = stringResource(R.string.add_card_screen_number_title),
            text = state.cardItem.cardNumber,
            placeHolder = stringResource(R.string.add_card_screen_number_hint),
            onTextChange = {
                onAction(AddCardActions.UpdateCardNumber(it.take(16)))
            },
            keyboardType = KeyboardType.Decimal,
            visualTransformation = CardNumberTransformation(),
            type = ADD_INPUT_TYPE_CARD
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.add_card_screen_type_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        CardTypeButton(
            cardTypeText = stringResource(R.string.add_card_button_credit),
            cardType = CARD_TYPE_CREDIT,
            selectedCardType = state.cardItem.cardType,
            iconImage = ImageVector.vectorResource(R.drawable.ic_credit_card_24_outlined),
            contentDescription = "Select Credit Card Button",
        ) {
            onAction(AddCardActions.SelectCardType(it))
        }
        Spacer(modifier = Modifier.height(8.dp))
        CardTypeButton(
            cardTypeText = stringResource(R.string.add_card_button_debit),
            cardType = CARD_TYPE_DEBIT,
            selectedCardType = state.cardItem.cardType,
            iconImage = ImageVector.vectorResource(R.drawable.ic_finance_chip_24_outlined),
            contentDescription = "Select Debit Card Button",
        ) {
            onAction(AddCardActions.SelectCardType(it))
        }

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
                onAction(AddCardActions.ClickAddButton)
            }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(R.string.add_spending_screen_add_button),
                fontSize = 18.sp
            )
        }
    }
}

@Composable
private fun CardTypeButton(
    cardTypeText: String,
    cardType: Int,
    selectedCardType: Int,
    iconImage: ImageVector,
    contentDescription: String,
    onRowClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if(cardType == selectedCardType) {
                    turquoise
                } else {
                    outlineTextFieldBorder
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = {
                onRowClick(cardType)
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp),
            imageVector = iconImage,
            contentDescription = contentDescription,
            tint = getCardTypeText(cardType == selectedCardType)
        )
        Text(
            text = cardTypeText,
            color = getCardTypeText(cardType == selectedCardType),
        )
    }
}

private fun getCardTypeText(isSelected: Boolean): Color = if(isSelected) {
    turquoiseTextColor
} else {
    inputTextColor
}

private fun showToast(
    context: Context,
    message: Int
) {
    Toast.makeText(
        context,
        context.getString(message),
        Toast.LENGTH_SHORT
    ).show()
}

/*
    SoftKeyboard 감지 유일하게 성공한 예제
 */
@Composable
fun keyboardVisibility(): State<Boolean> {
    val keyboardVisibilityState = rememberSaveable { mutableStateOf(false) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardVisibilityState.value = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }
    return keyboardVisibilityState
}

@Composable
@Preview(showBackground = true)
fun AddCardScreenPreview() {
    MyBudgetTheme {
        AddCardScreen(
            state = AddCardState(),
        ) {

        }
    }
}