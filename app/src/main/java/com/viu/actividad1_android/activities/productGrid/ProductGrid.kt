package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viu.actividad1_android.data.product.Product

/**
 * Grid reutilizable que muestra una lista de productos en formato de cuadrícula.
 *
 * Cada producto se representa mediante un [ProductCard], y el grid permite
 * gestionar la navegación al detalle mediante el callback [onProductClick].
 *
 * @param products Lista de productos a mostrar.
 * @param onProductClick Acción que se ejecuta al pulsar un producto, devolviendo su ID.
 */
@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product = product,
                onProductClick = onProductClick
            )
        }
    }
}
