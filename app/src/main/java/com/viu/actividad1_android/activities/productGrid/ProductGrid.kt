package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.viu.actividad1_android.data.product.Product

@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(products) { product ->
            ProductCard(product, onProductClick)
        }
    }
}
