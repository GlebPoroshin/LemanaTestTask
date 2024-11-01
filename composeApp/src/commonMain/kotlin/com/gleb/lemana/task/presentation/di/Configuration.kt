package com.gleb.lemana.task.presentation.di

import com.gleb.lemana.task.data.network.di.networkModule
import org.koin.dsl.koinApplication

fun koinConfiguration() = koinApplication {
    modules(networkModule)
}
