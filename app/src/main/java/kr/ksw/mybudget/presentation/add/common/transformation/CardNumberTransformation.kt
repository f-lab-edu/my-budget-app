package kr.ksw.mybudget.presentation.add.common.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberTransformation: VisualTransformation {
    // Making XXXX XXXX XXXX XXXX string.
    private val creditCardOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) {
            text.text.substring(0..15)
        } else {
            text.text
        }
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            // Adding space after every 4th digit
            if (i % 4 == 3 && i != 15) {
                out += " "
            }
        }
        return TransformedText(
            AnnotatedString(out),
            creditCardOffsetMapping
        )
    }
}