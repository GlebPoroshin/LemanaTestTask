package com.gleb.lemana.task.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.gleb.lemana.task.presentation.utils.Colors.mainGrey

@Composable
fun GradientSeparator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        mainGrey,
                        Color.White.copy(0.3f),
                        Color.White.copy(0f)
                    )
                )
            )
    )
}