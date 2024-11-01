package com.gleb.lemana.task.data.network.di

import com.gleb.lemana.task.data.network.service.ProductsServiceImpl
import com.gleb.lemana.task.domain.service.ProductsService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    single<ProductsService> { ProductsServiceImpl(get()) }
}