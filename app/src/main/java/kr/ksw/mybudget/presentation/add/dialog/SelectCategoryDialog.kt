package kr.ksw.mybudget.presentation.add.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ksw.mybudget.domain.model.spending.SpendingType
import kr.ksw.mybudget.ui.theme.MyBudgetTheme
import kr.ksw.mybudget.ui.theme.inputTextColor
import kr.ksw.mybudget.ui.theme.turquoise

@Composable
fun SelectCategoryDialog(
    onSelectedCategory: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = 480.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 10.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                count = SpendingType.entries.size,
                key = { index ->
                    SpendingType.entries[index].subCategory
                }
            ) { index ->
                val category = SpendingType.entries[index]
                Row(
                    modifier = Modifier
                        .clickable {
                            onSelectedCategory(index)
                        }
                        .border(
                            shape = RoundedCornerShape(4.dp),
                            width = 1.5.dp,
                            color = turquoise
                        )
                        .padding(
                            start = 6.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(category.iconId),
                        contentDescription = stringResource(category.titleId),
                        tint = inputTextColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier
                            .padding(bottom = 2.dp),
                        text = stringResource(category.titleId),
                        fontSize = 15.sp,
                        color = inputTextColor,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectCategoryDialogPreview() {
    MyBudgetTheme {
        Surface {
            SelectCategoryDialog {

            }
        }
    }
}