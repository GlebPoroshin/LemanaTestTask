package com.gleb.lemana.task.data.database

import com.gleb.lemana.task.LemenaTestAppDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface ShoppingListRepository {
    suspend fun getAllProductIds(): Set<Int>
    suspend fun insertProductId(productId: Int)
    suspend fun deleteProductId(productId: Int)
}

class ShoppingListRepositoryImpl(
    db: LemenaTestAppDb
) : ShoppingListRepository {

    private val queries = db.shoppingListQueries

    override suspend fun getAllProductIds(): Set<Int> = withContext(Dispatchers.IO) {
        queries.getAllProductIds()
            .executeAsList()
            .map { it.toInt() }
            .toSet()
    }

    override suspend fun insertProductId(productId: Int) = withContext(Dispatchers.IO) {
        queries.insertProductId(productId.toLong())
    }

    override suspend fun deleteProductId(productId: Int) = withContext(Dispatchers.IO) {
        queries.deleteProductId(productId.toLong())
    }
}
