package com.gleb.lemana.task.data.database

import com.gleb.lemana.task.LemenaTestAppDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface CartRepository {
    suspend fun getAllCartItems(): Map<Int, Int>
    suspend fun addProductToCart(productId: Int)
    suspend fun removeProductFromCart(productId: Int)
    suspend fun updateProductQuantity(productId: Int, quantity: Int)
}

class CartRepositoryImpl(
    db: LemenaTestAppDb
) : CartRepository {

    private val cartQueries = db.cartQueries

    override suspend fun getAllCartItems(): Map<Int, Int> = withContext(Dispatchers.IO) {
        cartQueries.selectAllCartItems()
            .executeAsList()
            .associate { it.product_id.toInt() to it.quantity.toInt() }
    }

    override suspend fun addProductToCart(productId: Int) = withContext(Dispatchers.IO) {
        cartQueries.insertProduct(productId.toLong(), 1)
    }

    override suspend fun removeProductFromCart(productId: Int) = withContext(Dispatchers.IO) {
        cartQueries.deleteProduct(productId.toLong())
    }

    override suspend fun updateProductQuantity(productId: Int, quantity: Int) = withContext(Dispatchers.IO) {
        cartQueries.updateQuantity(quantity.toLong(), productId.toLong())
    }
}
