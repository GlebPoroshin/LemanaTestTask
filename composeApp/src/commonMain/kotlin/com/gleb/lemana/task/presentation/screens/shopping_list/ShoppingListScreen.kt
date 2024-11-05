package com.gleb.lemana.task.presentation.screens.shopping_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.gleb.lemana.task.presentation.components.ErrorDisplayingComponent
import com.gleb.lemana.task.presentation.components.PrimaryButton
import com.gleb.lemana.task.presentation.components.ShoppingListItem
import com.gleb.lemana.task.presentation.utils.Colors.primary

class ShoppingListScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: ShoppingListScreenModel = koinScreenModel()
        val state by screenModel.state.collectAsState()

        when (val currentState = state) {
            is ShoppingListScreenModel.State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = primary)
                }
            }
            is ShoppingListScreenModel.State.Content -> {
                val products = currentState.products
                val selectedItems = currentState.selectedItems

                    if (products.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(start = 16.dp, end = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top)
                        ) {
                            item {
                                Text(
                                    text = "Shopping List",
                                    style = TextStyle(
                                        color = primary,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                )
                                Spacer(Modifier.height(32.dp))
                            }
                            items(products, key = { it.id }) { item ->
                                ShoppingListItem(
                                    price = item.price,
                                    title = item.title,
                                    imageUri = item.image,
                                    description = item.description,
                                    isSelected = item.id in selectedItems,
                                    onSelectedChange = { isSelected ->
                                        screenModel.processIntent(
                                            ShoppingListScreenModel.Intent.SelectItem(
                                                productId = item.id,
                                                isSelected = isSelected
                                            )
                                        )
                                    }
                                )
                            }
                            item {
                                PrimaryButton(
                                    text = "Add selected to cart",
                                    trailingIcon = Icons.Outlined.ShoppingCart,
                                    modifier = Modifier
                                        .padding(bottom = 6.dp),
                                    onClick = {
                                        screenModel.processIntent(
                                            ShoppingListScreenModel.Intent.AddSelectedToCart
                                        )
                                    }
                                )
                            }
                            item { Spacer(Modifier.height(64.dp)) }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "Shopping list is empty :(",
                                style = TextStyle(
                                    color = primary,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
            }
            is ShoppingListScreenModel.State.Error -> {
                ErrorDisplayingComponent(
                    message = currentState.message,
                    onClick = {
                        screenModel.processIntent(ShoppingListScreenModel.Intent.LoadShoppingList)
                    }
                )
            }
        }
    }
}
