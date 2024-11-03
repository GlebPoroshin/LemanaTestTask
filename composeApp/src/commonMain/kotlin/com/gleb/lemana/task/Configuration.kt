package com.gleb.lemana.task

import com.gleb.lemana.task.data.database.di.dbModule
import com.gleb.lemana.task.data.network.di.networkModule
import com.gleb.lemana.task.presentation.di.presentationModule
import org.koin.dsl.koinApplication

fun koinConfiguration() = koinApplication {
    modules(networkModule, presentationModule, dbModule)
}
