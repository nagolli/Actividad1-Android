package com.viu.actividad1_android.activities.productGrid

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.data.product.repository.ProductRepository
import kotlinx.coroutines.launch

//Modelo de ProductGrid, implementando el patron MVVM.
//Define e importa estado de ProductGridState, define las funciones de ProductGridEvent.
class ProductGridViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    var state by mutableStateOf(ProductGridState())
        private set

    init {
        onEvent(ProductGridEvent.LoadProducts)
    }

    fun onEvent(event: ProductGridEvent) {
        when (event) {

            ProductGridEvent.LoadProducts -> {
                loadProducts()
            }

            is ProductGridEvent.OnFilterChanged -> {
                state = state.copy(filter = event.filter)
            }

            ProductGridEvent.ApplyFilters -> {
                applyFilters()
            }

            is ProductGridEvent.OnProductClick -> {
                // navegación o acción
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val products = repository.getProducts()
                state = state.copy(products = products, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun applyFilters() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val products = repository.filterProducts(state.filter)
                state = state.copy(products = products, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}
