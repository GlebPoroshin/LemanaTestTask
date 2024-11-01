package com.gleb.lemana.task.data.network.service

import com.gleb.lemana.task.data.network.NetworkConfig
import com.gleb.lemana.task.data.network.model.ProductNetworkModel
import com.gleb.lemana.task.data.network.model.asDomainModel
import com.gleb.lemana.task.data.network.model.asDomainModels
import com.gleb.lemana.task.domain.model.ProductDomainModel
import com.gleb.lemana.task.domain.service.ProductsService
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ProductsServiceImpl(
    private val httpClient: HttpClient
) : ProductsService {

    override suspend fun fetchAll(limit: Int?, isAsc: Boolean): Result<List<ProductDomainModel>> {
        Napier.d(tag = "LemanaApp") { "ProductsService.fetchAll called. limit = $limit, isAsc = $isAsc" }
        return try {
            withContext(Dispatchers.IO) {
                val result = httpClient.get("${NetworkConfig.BASE_ENDPOINT}/products").body<List<ProductNetworkModel>>()
                Result.success(result.asDomainModels()).also {
                    Napier.d(tag = "LemanaApp") { "ProductsService.fetchAll result success = ${it.getOrNull()}" }
                }
            }
        } catch (exception: Exception) {
            Napier.e(tag = "LemanaApp") { "ProductsService.fetchAll result failure. exception: ${exception.message}" }
            Result.failure(exception)
        }
    }

    override suspend fun getProduct(id: Int): Result<ProductDomainModel> {
        Napier.d(tag = "LemanaApp") { "ProductsService.getProduct called. id = $id" }
        return try {
            withContext(Dispatchers.IO) {
                val result = httpClient.get("${NetworkConfig.BASE_ENDPOINT}/products/$id").body<ProductNetworkModel>()
                Result.success(result.asDomainModel()).also {
                    Napier.d(tag = "LemanaApp") { "ProductsService.getProduct result success = ${it.getOrNull()}" }
                }
            }
        } catch (exception: Exception) {
            Napier.e(tag = "LemanaApp") { "ProductsService.getProduct result failure. exception: ${exception.message}" }
            Result.failure(exception)
        }
    }
}
