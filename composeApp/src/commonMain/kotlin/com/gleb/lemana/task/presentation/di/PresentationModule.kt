package com.gleb.lemana.task.presentation.di

import com.gleb.lemana.task.presentation.screens.main.MainScreenModel
import org.koin.dsl.module

val presentationModule = module {
    factory {
        MainScreenModel(
            cartRepository = get(),
            productsService = get(),
            shoppingListRepository = get()
        )
    }
}