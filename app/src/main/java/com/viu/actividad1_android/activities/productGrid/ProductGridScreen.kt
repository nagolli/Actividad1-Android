package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

//Vista del grid de productos, implementando el patron MVVM.
//Primero presenta unos filtros y luego el grid de productos filtrados.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGridScreen(
    viewModel: ProductGridViewModel = viewModel(),
    onProductClick: (Int) -> Unit
) {
    val state = viewModel.state
    Column {
        CollapsibleFilters {
            ProductFilters(
                filter = state.filter,
                categories = state.categories,
                suppliers = state.suppliers,
                onFilterChange = { viewModel.onEvent(ProductGridEvent.OnFilterChanged(it)) },
                maxPrice = state.maxPrice
            )
        }

        ProductGrid(
            products = state.products,
            onProductClick = { id -> viewModel.onEvent(ProductGridEvent.OnProductClick(id)) }
        )
    }
}
