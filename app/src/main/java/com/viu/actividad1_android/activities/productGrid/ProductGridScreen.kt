package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.viu.actividad1_android.R
import com.viu.actividad1_android.reusableComponents.CollapsibleSection

/**
 * Pantalla principal del grid de productos.
 *
 * Implementa el patrón MVVM mostrando primero los filtros disponibles
 * y posteriormente el grid de productos filtrados. Gestiona los eventos
 * del usuario enviándolos al ViewModel correspondiente.
 *
 * @param viewModel ViewModel asociado a la pantalla.
 * @param onProductClick Acción que se ejecuta al seleccionar un producto.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGridScreen(
    viewModel: ProductGridViewModel = viewModel(),
    onProductClick: (Int) -> Unit
) {
    val state = viewModel.state

    Column(modifier = Modifier.padding(8.dp)) {

        CollapsibleSection(title = stringResource(R.string.filters_title)) {
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
            onProductClick = { id ->
                viewModel.onEvent(ProductGridEvent.OnProductClick(id))
                onProductClick(id)
            }
        )
    }
}
