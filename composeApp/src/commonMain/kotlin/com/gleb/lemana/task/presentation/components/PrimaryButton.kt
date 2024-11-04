package com.gleb.lemana.task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gleb.lemana.task.presentation.utils.Colors.onPrimary
import com.gleb.lemana.task.presentation.utils.Colors.primary

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    Row(
        modifier = modifier
            .background(
                color = primary,
                shape = RoundedCornerShape(44.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        leadingIcon?.let { icon ->
            Icon(
                imageVector = icon,
                tint = onPrimary,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = text ,
            style = TextStyle(
                color = onPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )
        trailingIcon?.let { icon ->
            Icon(
                imageVector = icon,
                tint = onPrimary,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}