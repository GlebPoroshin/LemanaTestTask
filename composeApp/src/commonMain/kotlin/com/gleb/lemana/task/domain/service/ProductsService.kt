package com.gleb.lemana.task.domain.service

import com.gleb.lemana.task.domain.model.ProductDomainModel

interface ProductsService {
    suspend fun fetchAll(
        limit: Int? = null,
        isAsc: Boolean = true
    ): Result<List<ProductDomainModel>>

    suspend fun getProduct(id: Int): Result<ProductDomainModel>
}