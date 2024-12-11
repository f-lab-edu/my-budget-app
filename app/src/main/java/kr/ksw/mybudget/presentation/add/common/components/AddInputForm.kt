package kr.ksw.mybudget.presentation.add.common.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import kr.ksw.mybudget.ui.theme.inputHintTextColor
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.outlineTextFieldBorder
import kr.ksw.mybudget.ui.theme.turquoise

const val ADD_INPUT_TYPE_TEXT = 0
const val ADD_INPUT_TYPE_NUMBER = 1
const val ADD_INPUT_TYPE_CARD = 2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInputForm(
    title: String,
    text: String,
    placeHolder: String,
    onTextChange: (String) -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        focusedIndicatorColor = turquoise,
        unfocusedIndicatorColor = outlineTextFieldBorder,
    ),
    type: Int = ADD_INPUT_TYPE_TEXT
) {
    val textValue = if(keyboardType == KeyboardType.Decimal &&
        text == "0" &&
        type == ADD_INPUT_TYPE_NUMBER
    ) {
        ""
    } else {
        text
    }
    val onValueChange = { value: String ->
        if(keyboardType == KeyboardType.Decimal) {
            if (value.length < 10 && value.isDigitsOnly()) {
                onTextChange(value)
            }
        } else {
            onTextChange(value)
        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
    Spacer(modifier = Modifier.height(8.dp))
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        value = textValue,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(
            color = inputTextColor
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(turquoise),
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = textValue,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = placeHolder,
                        color = inputHintTextColor
                    )
                },
                singleLine = true,
                colors = colors,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = RoundedCornerShape(8.dp),
                    )
                },
                contentPadding = PaddingValues(12.dp)
            )
        }
    )
}