package com.viu.actividad1_android.ui.topMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.R
import com.viu.actividad1_android.activities.cart.CartViewModel

/**
 * Barra superior de navegación de la aplicación.
 *
 * Muestra el logotipo y tres acciones principales:
 * - Acceso al catálogo de productos.
 * - Acceso al carrito.
 * - Acceso al perfil o historial del usuario.
 *
 * @param onChessClick Acción al pulsar el icono de productos.
 * @param onCartClick Acción al pulsar el icono del carrito.
 * @param onUserClick Acción al pulsar el icono del usuario.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenu(
    cartViewModel: CartViewModel,
    onChessClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onUserClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.topmenu_logo_description),
                modifier = Modifier
                    .size(200.dp)
                    .padding(0.dp)
            )
        },
        modifier = Modifier.height(100.dp),
        actions = {

            IconButton(
                onClick = onChessClick,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Extension,
                    contentDescription = stringResource(R.string.topmenu_products_description),
                    modifier = Modifier.size(32.dp)
                )
            }

            val state = cartViewModel.state.value
            val itemCount = state.items.sumOf { it.quantity }

            BadgedBox(
                badge = {
                    if (itemCount > 0) {
                        Badge {
                            Text(itemCount.toString())
                        }
                    }
                }
            ) {
                IconButton(onClick = onCartClick) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito"
                    )
                }
            }

            IconButton(
                onClick = onUserClick,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.topmenu_user_description),
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
