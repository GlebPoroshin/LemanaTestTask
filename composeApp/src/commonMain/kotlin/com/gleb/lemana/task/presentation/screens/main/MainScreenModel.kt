package com.gleb.lemana.task.presentation.screens.main

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.gleb.lemana.task.data.database.CartRepository
import com.gleb.lemana.task.data.database.ShoppingListRepository
import com.gleb.lemana.task.domain.model.ProductDomainModel
import com.gleb.lemana.task.domain.service.ProductsService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainScreenModel(
    private val cartRepository: CartRepository,
    private val productsService: ProductsService,
    private val shoppingListRepository: ShoppingListRepository,
) : StateScreenModel<MainScreenModel.State>(State.Loading) {

    sealed class State {
        object Loading : State()
        data class Content(val products: List<ProductDomainModel>) : State()
        data class Error(val message: String) : State()
    }

    sealed class Intent {
        object LoadProducts : Intent()
        object LoadMoreProducts : Intent()
        data class AddToShoppingList(val productId: Int) : Intent()
        data class RemoveFromShoppingList(val productId: Int) : Intent()
        data class ChangeInCartCount(val productId: Int, val count: Int) : Intent()
    }

    private var currentLimit = 10
    private val pageSize = 10
    var isLoadingMore = false
        private set

    private var cartItems: Map<Int, Int> = emptyMap()
    private var shoppingListIds: Set<Int> = emptySet()

    fun processIntent(intent: Intent) {
        when (intent) {
            is Intent.LoadProducts -> loadProducts()
            is Intent.LoadMoreProducts -> loadMoreProducts()
            is Intent.AddToShoppingList -> addToShoppingList(intent.productId)
            is Intent.RemoveFromShoppingList -> removeFromShoppingList(intent.productId)
            is Intent.ChangeInCartCount -> changeInCartCount(intent.productId, intent.count)
        }
    }

    init {
        processIntent(Intent.LoadProducts)
        observeShoppingListChanges()
        observeCartChanges()
    }

    private fun loadProducts() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            currentLimit = pageSize

            shoppingListIds = shoppingListRepository.getAllProductIds()
            cartItems = cartRepository.getAllCartItems()

            productsService.fetchAll(limit = currentLimit)
                .onSuccess { products ->
                    val updatedProducts = products.map { product ->
                        product.copy(
                            isLiked = product.id in shoppingListIds,
                            inCartCount = cartItems[product.id] ?: 0
                        )
                    }
                    mutableState.value = State.Content(updatedProducts)
                }
                .onFailure { throwable ->
                    mutableState.value = State.Error(throwable.message ?: "Неизвестная ошибка")
                }
        }
    }

    private fun loadMoreProducts() {
        if (isLoadingMore) return
        isLoadingMore = true
        screenModelScope.launch {
            val currentState = state.value
            if (currentState is State.Content) {
                val currentProducts = currentState.products
                currentLimit += pageSize

                shoppingListIds = shoppingListRepository.getAllProductIds()
                cartItems = cartRepository.getAllCartItems()

                productsService.fetchAll(limit = currentLimit)
                    .onSuccess { allProducts ->
                        val newProducts =
                            allProducts.subList(currentProducts.size, allProducts.size)
                        val updatedNewProducts = newProducts.map { product ->
                            product.copy(
                                isLiked = product.id in shoppingListIds,
                                inCartCount = cartItems[product.id] ?: 0
                            )
                        }
                        if (updatedNewProducts.isNotEmpty()) {
                            mutableState.value = State.Content(currentProducts + updatedNewProducts)
                        }
                        isLoadingMore = false
                    }
                    .onFailure { _ ->
                        isLoadingMore = false
                    }
            } else {
                isLoadingMore = false
            }
        }
    }

    private fun observeShoppingListChanges() {
        Napier.d(tag = "LemanaApp") { "observeShoppingListChanges called" }
        screenModelScope.launch {
            shoppingListIds = shoppingListRepository.getAllProductIds().also { savedShoppingList ->
                Napier.d(tag = "LemanaApp") { "observeShoppingListChanges result = $savedShoppingList" }
            }
            updateProductsIsLiked()
        }
    }

    private fun updateProductsIsLiked() {
        Napier.d(tag = "LemanaApp") { "updateProductsIsLiked called" }
        val currentState = state.value
        if (currentState is State.Content) {
            val updatedProducts = currentState.products.map { product ->
                product.copy(isLiked = product.id in shoppingListIds)
            }
            mutableState.value = State.Content(updatedProducts).also { updatedProductList ->
                Napier.d(tag = "LemanaApp") {
                    "updated liked product list =${updatedProductList.products.map { it.isLiked }}"
                }
            }
        }
    }

    private fun addToShoppingList(productId: Int) {
        Napier.d(tag = "LemanaApp") { "addToShoppingList id = $productId" }
        screenModelScope.launch {
            shoppingListRepository.insertProductId(productId)
            observeShoppingListChanges()
        }
    }

    private fun removeFromShoppingList(productId: Int) {
        Napier.d(tag = "LemanaApp") { "removeFromShoppingList id = $productId" }
        screenModelScope.launch {
            shoppingListRepository.deleteProductId(productId)
            observeShoppingListChanges()
        }
    }

    private fun observeCartChanges() {
        Napier.d(tag = "LemanaApp") { "observeCartChanges called" }
        screenModelScope.launch {
            cartItems = cartRepository.getAllCartItems().also { dbCartItems ->
                Napier.d(tag = "LemanaApp") {
                    "observeCartChanges saved cart items = $dbCartItems"
                }
            }
            updateProductsInCartCount()
        }
    }

    private fun updateProductsInCartCount() {
        Napier.d(tag = "LemanaApp") { "updateProductsInCartCount called" }
        val currentState = state.value
        if (currentState is State.Content) {
            val updatedProducts = currentState.products.map { product ->
                val count = cartItems[product.id] ?: 0
                product.copy(inCartCount = count)
            }
            mutableState.value = State.Content(updatedProducts).also { updatedProductList ->
                Napier.d(tag = "LemanaApp") {
                    "updated cart count product list = ${updatedProductList.products.map { it.inCartCount }}"
                }
            }
        }
    }

    private fun changeInCartCount(productId: Int, count: Int) {
        screenModelScope.launch {
            when (count) {
                0 -> {
                    cartRepository.removeProductFromCart(productId)
                }

                1 -> {
                    cartRepository.addProductToCart(productId)
                }

                else -> {
                    cartRepository.updateProductQuantity(productId, count)
                }
            }
            observeCartChanges()
        }
    }
}
