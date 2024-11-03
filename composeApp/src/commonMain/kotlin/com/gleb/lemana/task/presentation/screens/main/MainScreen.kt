package com.gleb.lemana.task.presentation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
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
                        screenModel.processIntent(MainScreenModel.Intent.AddToShoppingList(productId))
                    },
                    onRemoveFromShoppingList = { productId ->
                        screenModel.processIntent(MainScreenModel.Intent.RemoveFromShoppingList(productId))
                    },
                    onCartCountChange = { productId, count ->
                        screenModel.processIntent(MainScreenModel.Intent.ChangeInCartCount(productId, count))
                    },
                    isLoadingMore = screenModel.isLoadingMore
                )
            }

            is MainScreenModel.State.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = currentState.message)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { screenModel.processIntent(MainScreenModel.Intent.LoadProducts) }) {
                            Text(text = "Попробовать снова")
                        }
                    }
                }
            }
        }
    }
}
