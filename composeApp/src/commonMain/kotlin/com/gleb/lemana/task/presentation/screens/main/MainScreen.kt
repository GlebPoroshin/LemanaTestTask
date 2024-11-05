package com.gleb.lemana.task.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.gleb.lemana.task.presentation.components.ErrorDisplayingComponent
import com.gleb.lemana.task.presentation.components.GradientSeparator
import com.gleb.lemana.task.presentation.components.ProductList
import com.gleb.lemana.task.presentation.utils.Colors.primary

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<MainScreenModel>()
        val state by screenModel.state.collectAsState()

        when (val currentState = state) {
            is MainScreenModel.State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primary)
                }
            }

            is MainScreenModel.State.Content -> {
                ProductList(
                    products = currentState.products,
                    onLoadMore = {
                        screenModel.processIntent(MainScreenModel.Intent.LoadMoreProducts)
                    },
                    onAddToShoppingList = { productId ->
                        screenModel.processIntent(
                            MainScreenModel.Intent.AddToShoppingList(
                                productId
                            )
                        )
                    },
                    onRemoveFromShoppingList = { productId ->
                        screenModel.processIntent(
                            MainScreenModel.Intent.RemoveFromShoppingList(
                                productId
                            )
                        )
                    },
                    onCartCountChange = { productId, count ->
                        screenModel.processIntent(
                            MainScreenModel.Intent.ChangeInCartCount(
                                productId,
                                count
                            )
                        )
                    },
                    isLoadingMore = screenModel.isLoadingMore
                )
            }

            is MainScreenModel.State.Error -> {
                ErrorDisplayingComponent(
                    message = currentState.message,
                    onClick = {
                        screenModel.processIntent(MainScreenModel.Intent.LoadProducts)
                    }
                )
            }
        }
    }
}
