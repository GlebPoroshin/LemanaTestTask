package com.gleb.lemana.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.gleb.lemana.task.presentation.components.BottomBar
import com.gleb.lemana.task.presentation.components.bottomBarItems
import com.gleb.lemana.task.presentation.screens.main.MainScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Napier.base(DebugAntilog())
            Navigator(MainScreen()) { navigator ->
                var selectedScreenIndex by remember { mutableIntStateOf(0) }

                Box(modifier = Modifier.fillMaxSize()) {
                    CurrentScreen()
                    BottomBar(
                        modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
                        items = bottomBarItems,
                        selectedIndex = selectedScreenIndex,
                        onScreenChanged = { screen ->
                            selectedScreenIndex = screen
                            navigator.replaceAll(bottomBarItems[screen].screen)
                        }
                    )
                }
        }
    }
}
