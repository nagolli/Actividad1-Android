package com.viu.actividad1_android.ui.topMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenu(
    onChessClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onUserClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo), // tu imagen
                contentDescription = "Logo",
                modifier = Modifier.size(160.dp).padding(0.dp)
            )
        },
        modifier = Modifier.height(80.dp),
        actions = {
            IconButton(onClick = onChessClick) {
                Icon(
                    Icons.Filled.Extension,
                    contentDescription = "Productos"
                )
            }
            IconButton(onClick = onCartClick) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Carrito"
                )
            }
            IconButton(onClick = onUserClick) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Historial"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
