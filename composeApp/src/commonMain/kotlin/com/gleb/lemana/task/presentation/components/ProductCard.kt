package com.gleb.lemana.task.presentation.components

import com.gleb.lemana.task.presentation.icons.SelectedLike
import com.gleb.lemana.task.presentation.icons.UnSelectedLike
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.gleb.lemana.task.presentation.icons.Star
import com.gleb.lemana.task.presentation.utils.Colors.mainGrey
import com.gleb.lemana.task.presentation.utils.Colors.onPrimary
import com.gleb.lemana.task.presentation.utils.Colors.secondaryGrey
import com.gleb.lemana.task.presentation.utils.Colors.yellow
import com.gleb.lemana.task.presentation.utils.SpacerHeight
import com.gleb.lemana.task.presentation.utils.SpacerWidth

@Composable
fun ProductCard(
    title: String,
    price: String,
    rating: String,
    imageUri: String,
    description: String,
    modifier: Modifier = Modifier,
    inCartCount: Int = 0,
    isLiked: Boolean = false,
    onIsLikedChanged: (Boolean) -> Unit,
    onCartCountChanged: (Int) -> Unit,
) {
    var isLoading by remember { mutableStateOf(true) }
    Column(
        modifier = modifier.background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = imageUri,
                contentDescription = title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
            )
            Icon(
                modifier = Modifier
                    .padding(14.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable { onIsLikedChanged(!isLiked) },
                contentDescription = "is $title in shopping list",
                tint = onPrimary,
                imageVector = if (isLiked) Icons.SelectedLike else Icons.UnSelectedLike,
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { onIsLikedChanged(!isLiked) },
                    contentDescription = null,
                    imageVector = Icons.Star,
                    tint = yellow
                )
                SpacerWidth(4.dp)
                Text(
                    text = rating,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        SpacerHeight(8.dp)
        Text(
            text = title,
            style = TextStyle(
                color = mainGrey,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        SpacerHeight(4.dp)
        Text(
            text = description,
            style = TextStyle(
                color = secondaryGrey,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal
            )
        )
        SpacerHeight(12.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = price,
                style = TextStyle(
                    color = mainGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(Modifier.weight(1f))
            AddToCartComponent(
                inCartCount = inCartCount,
                onCartCountChange = onCartCountChanged
            )
        }
    }
}
