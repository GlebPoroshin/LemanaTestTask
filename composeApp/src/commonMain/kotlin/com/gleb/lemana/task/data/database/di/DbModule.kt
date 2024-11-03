package com.gleb.lemana.task.data.database.di

import com.gleb.lemana.task.LemenaTestAppDb
import com.gleb.lemana.task.data.database.CartRepository
import com.gleb.lemana.task.data.database.CartRepositoryImpl
import com.gleb.lemana.task.data.database.ShoppingListRepository
import com.gleb.lemana.task.data.database.ShoppingListRepositoryImpl
import com.gleb.lemana.task.utils.createDriver
import org.koin.dsl.module

val dbModule = module {
    single<LemenaTestAppDb> { LemenaTestAppDb(createDriver()) }
    single<ShoppingListRepository> { ShoppingListRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
}
