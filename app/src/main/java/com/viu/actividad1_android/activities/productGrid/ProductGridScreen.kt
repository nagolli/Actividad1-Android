package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

//Vista del grid de productos, implementando el patron MVVM.
//Primero presenta unos filtros y luego el grid de productos filtrados.
@Composable
fun ProductGridScreen(
    viewModel: ProductGridViewModel = viewModel(),
    onProductClick: (Int) -> Unit
) {
    val state = viewModel.state

    Column {
        /*ProductFilters(
            filter = state.filter,
            onFilterChange = { viewModel.onEvent(ProductGridEvent.OnFilterChanged(it)) },
            onApply = { viewModel.onEvent(ProductGridEvent.ApplyFilters) }
        )*/

        ProductGrid(
            products = state.products,
            onProductClick = { id -> viewModel.onEvent(ProductGridEvent.OnProductClick(id)) }
        )
    }
}