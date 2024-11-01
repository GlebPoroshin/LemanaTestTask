package com.gleb.lemana.task.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gleb.lemana.task.presentation.utils.Colors.mainGrey
import com.gleb.lemana.task.presentation.utils.Colors.secondaryGrey

@Composable
fun AddToCartComponent(
    inCartCount: Int,
    onCartCountChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (inCartCount == 0) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp)
                    .clickable { onCartCountChange(1) },
                contentDescription = "Add to cart",
                imageVector = Icons.Outlined.ShoppingCart,
            )
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 1.dp,
                        color = secondaryGrey,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onCartCountChange(inCartCount - 1)
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "-",
                    style = TextStyle(
                        color = mainGrey,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Text(
                text = inCartCount.toString(),
                style = TextStyle(
                    color = mainGrey,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 1.dp,
                        color = secondaryGrey,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onCartCountChange(inCartCount + 1)
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "+",
                    style = TextStyle(
                        color = mainGrey,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
