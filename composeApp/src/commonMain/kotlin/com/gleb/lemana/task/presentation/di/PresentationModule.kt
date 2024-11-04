package com.gleb.lemana.task.presentation.di

import com.gleb.lemana.task.presentation.screens.main.MainScreenModel
import com.gleb.lemana.task.presentation.screens.shopping_list.ShoppingListScreenModel
import org.koin.dsl.module

val presentationModule = module {
    factory {
        MainScreenModel(
            cartRepository = get(),
            productsService = get(),
            shoppingListRepository = get()
        )
    }

    factory {
        ShoppingListScreenModel(
            cartRepository = get(),
            productsService = get(),
            shoppingListRepository = get()
        )
    }
}