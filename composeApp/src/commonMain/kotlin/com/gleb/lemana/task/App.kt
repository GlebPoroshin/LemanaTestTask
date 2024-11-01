package com.gleb.lemana.task

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.gleb.lemana.task.presentation.components.BottomBar
import com.gleb.lemana.task.presentation.di.koinConfiguration
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    MaterialTheme {
        Napier.base(DebugAntilog())
        KoinApplication(::koinConfiguration) {
            Scaffold(
                bottomBar = { BottomBar() }
            ) {
//                Navigator
            }
        }
    }
}
