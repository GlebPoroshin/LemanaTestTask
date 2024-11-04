package com.gleb.lemana.task.presentation.screens.shopping_list

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.gleb.lemana.task.data.database.CartRepository
import com.gleb.lemana.task.data.database.ShoppingListRepository
import com.gleb.lemana.task.domain.model.ProductDomainModel
import com.gleb.lemana.task.domain.service.ProductsService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

class ShoppingListScreenModel(
    private val productsService: ProductsService,
    private val shoppingListRepository: ShoppingListRepository,
    private val cartRepository: CartRepository
) : StateScreenModel<ShoppingListScreenModel.State>(State.Loading) {

    sealed class State {
        object Loading : State()
        data class Content(
            val products: List<ProductDomainModel>,
            val selectedItems: Set<Int>
        ) : State()

        data class Error(val message: String) : State()
    }

    sealed class Intent {
        object LoadShoppingList : Intent()
        data class SelectItem(val productId: Int, val isSelected: Boolean) : Intent()
        object AddSelectedToCart : Intent()
    }

    private var shoppingListIds: Set<Int> = emptySet()

    init {
        Napier.d(tag = "LemanaApp") { "ShoppingListScreenModel init" }
        processIntent(Intent.LoadShoppingList)
    }

    fun processIntent(intent: Intent) {
        Napier.d(tag = "LemanaApp") { "ShoppingListScreenModel processIntent: $intent" }
        when (intent) {
            is Intent.LoadShoppingList -> loadShoppingList()
            is Intent.SelectItem -> selectItem(intent.productId, intent.isSelected)
            is Intent.AddSelectedToCart -> addSelectedItemsToCart()
        }
    }

    private fun loadShoppingList() {
        Napier.d(tag = "LemanaApp") { "ShoppingListScreenModel loadShoppingList called" }
        screenModelScope.launch {
            mutableState.value = State.Loading

            shoppingListIds = shoppingListRepository.getAllProductIds().also {
                Napier.d(tag = "LemanaApp") {
                    "ShoppingListScreenModel loadShoppingList shoppingListIds: $it "
                }
            }

            if (shoppingListIds.isEmpty()) {
                mutableState.value = State.Content(
                    products = emptyList(),
                    selectedItems = emptySet()
                )
                return@launch
            }

            productsService.fetchProductsByIds(shoppingListIds.toList())
                .onSuccess { products ->
                    mutableState.value = State.Content(
                        products = products,
                        selectedItems = emptySet()
                    )
                }
                .onFailure { throwable ->
                    mutableState.value = State.Error(throwable.message ?: "Неизвестная ошибка")
                }
        }
    }

    private fun selectItem(productId: Int, isSelected: Boolean) {
        val currentState = state.value
        if (currentState is State.Content) {
            val updatedSelectedItems = if (isSelected) {
                currentState.selectedItems + productId
            } else {
                currentState.selectedItems - productId
            }
            mutableState.value = currentState.copy(selectedItems = updatedSelectedItems)
        }
    }

    private fun addSelectedItemsToCart() {
        val currentState = state.value
        if (currentState is State.Content) {
            screenModelScope.launch {
                currentState.selectedItems.forEach { productId ->
                    cartRepository.addProductToCart(productId)
                    shoppingListRepository.deleteProductId(productId)
                }
                processIntent(Intent.LoadShoppingList)
            }
        }
    }
}
