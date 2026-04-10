package com.viu.actividad1_android.activities.productGrid

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
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
                applyFilters()
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
                val maxPrice = products.maxOfOrNull { it.price }?.toFloat() ?: 0f
                state = state.copy(products = products, maxPrice = maxPrice, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun applyFilters() {
        viewModelScope.launch {
            Log.d("ProductGridVM", "Evento: ApplyFilters")
            state = state.copy(isLoading = true)
            try {
                val products = repository.filterProducts(state.filter)
                state = state.copy(products = products, isLoading = false)
                Log.d("ProductGridVM", "Resultado: "+products.size)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}

class ProductGridViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductGridViewModel::class.java)) {
            return ProductGridViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
