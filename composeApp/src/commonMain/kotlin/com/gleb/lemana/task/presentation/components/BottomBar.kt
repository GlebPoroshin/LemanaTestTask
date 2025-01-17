package com.gleb.lemana.task.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import cafe.adriel.voyager.core.screen.Screen
import com.gleb.lemana.task.presentation.screens.cart.CartScreen
import com.gleb.lemana.task.presentation.screens.main.MainScreen
import com.gleb.lemana.task.presentation.screens.shopping_list.ShoppingListScreen
import com.gleb.lemana.task.presentation.utils.Colors.onPrimary
import com.gleb.lemana.task.presentation.utils.Colors.primary
import com.gleb.lemana.task.presentation.utils.Colors.secondary

@Composable
fun BottomBar(
    items: List<TabItem>,
    onScreenChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
) {

    Row(
        modifier = modifier
            .background(
                color = primary,
                shape = RoundedCornerShape(44.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(44.dp, Alignment.CenterHorizontally),
    ) {
        items.fastForEachIndexed { index, item ->
            Column(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = secondary,
                        shape = CircleShape
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
            ) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            shape = CircleShape,
                            color = secondary
                        )
                )
                Icon(
                    tint = onPrimary,
                    imageVector = if (index == selectedIndex) {
                        item.filledIcon
                    } else item.outlinedIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onScreenChanged(index) }
                )
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            shape = CircleShape,
                            color = if (index == selectedIndex) onPrimary else secondary
                        )
                )
            }
        }
    }
}

class TabItem(
    val screen: Screen,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
)


val bottomBarItems = listOf(
    TabItem(
        screen = MainScreen(),
        filledIcon = Icons.Filled.Home,
        outlinedIcon = Icons.Outlined.Home
    ),
    TabItem(
        screen = ShoppingListScreen(),
        filledIcon = Icons.Filled.Favorite,
        outlinedIcon = Icons.Outlined.FavoriteBorder
    ),
    TabItem(
        screen = CartScreen(),
        filledIcon = Icons.Filled.ShoppingCart,
        outlinedIcon = Icons.Outlined.ShoppingCart
    ),
)
