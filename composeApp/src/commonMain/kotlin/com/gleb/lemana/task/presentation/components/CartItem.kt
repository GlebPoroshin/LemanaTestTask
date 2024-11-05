package com.gleb.lemana.task.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.gleb.lemana.task.presentation.utils.Colors.mainGrey
import com.gleb.lemana.task.presentation.utils.Colors.primary
import com.gleb.lemana.task.presentation.utils.SpacerHeight
import com.gleb.lemana.task.presentation.utils.SpacerWidth

@Composable
fun CartItem(
    count: Int,
    title: String,
    price: String,
    imageUri: String,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            SpacerWidth(16.dp)
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = mainGrey,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                SpacerHeight(12.dp)
                Text(
                    text = "$$price",
                    style = TextStyle(
                        color = primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
        AddToCartComponent(
            modifier = Modifier.align(Alignment.BottomEnd),
            inCartCount = count,
            onCartCountChange = onCountChange
        )
    }
}
