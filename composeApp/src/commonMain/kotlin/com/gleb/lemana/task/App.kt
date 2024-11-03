package com.gleb.lemana.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import cafe.adriel.voyager.navigator.Navigator
import com.gleb.lemana.task.presentation.screens.CartScreen
import com.gleb.lemana.task.presentation.screens.main.MainScreen
import com.gleb.lemana.task.presentation.screens.ShoppingListScreen
import com.gleb.lemana.task.presentation.utils.Colors.primary
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import lemana.composeapp.generated.resources.Res
import lemana.composeapp.generated.resources.compose_multiplatform
import lemana.composeapp.generated.resources.selected_like
import lemana.composeapp.generated.resources.uselected_like
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    MaterialTheme {
        Napier.base(DebugAntilog())
        KoinApplication(::koinConfiguration) {
            Navigator(MainScreen())
        }
    }
}

@Preview
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        Res.drawable.uselected_like to MainScreen(),
        Res.drawable.compose_multiplatform to CartScreen(),
        Res.drawable.compose_multiplatform to ShoppingListScreen(),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    Row(
        modifier = modifier
            .background(
                color = primary,
                shape = RoundedCornerShape(44.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(44.dp, Alignment.CenterHorizontally),
    ) {
        items.fastForEachIndexed { index, item ->
            Image(
                painter = painterResource(
                    if (selectedIndex == index) item.first else Res.drawable.selected_like),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
