package com.viu.actividad1_android.activities.productGrid

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.data.category.Category
import com.viu.actividad1_android.data.category.repository.CategoryRepository
import com.viu.actividad1_android.data.product.repository.ProductRepository
import com.viu.actividad1_android.data.supplier.repository.SupplierRepository
import kotlinx.coroutines.launch

//Modelo de ProductGrid, implementando el patron MVVM.
//Define e importa estado de ProductGridState, define las funciones de ProductGridEvent.
class ProductGridViewModel(
    private val productsRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val supplierRepository: SupplierRepository
) : ViewModel() {

    var state by mutableStateOf(ProductGridState())
        private set

    init {
        onEvent(ProductGridEvent.LoadProducts)
        onEvent(ProductGridEvent.LoadCategories)
        onEvent(ProductGridEvent.LoadSuppliers)
    }

    fun onEvent(event: ProductGridEvent) {
        when (event) {

            ProductGridEvent.LoadProducts -> {
                loadProducts()
            }

            ProductGridEvent.LoadCategories -> {
                loadCategories()
            }

            ProductGridEvent.LoadSuppliers -> {
                loadSuppliers()
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
                val products = productsRepository.getProducts()
                val maxPrice = products.maxOfOrNull { it.price }?.toFloat() ?: 0f
                state = state.copy(products = products, maxPrice = maxPrice, isLoading = false)
                println("🔍 [PRODUCT VIEW MODEL] products: ${state.products.size}")
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val categories = categoryRepository.getCategories()
                state = state.copy(categories = categories.map { Pair(it.id, it.name) })
                println("🔍 [PRODUCT VIEW MODEL] categories: ${state.categories.size}")
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            }
        }
    }

    private fun loadSuppliers() {
        viewModelScope.launch {
            try {
                val suppliers = supplierRepository.getSuppliers()
                state = state.copy(suppliers = suppliers.map { Pair(it.id, it.name) })
                println("🔍 [PRODUCT VIEW MODEL] suppliers: ${state.suppliers.size}")
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            }
        }
    }

    private fun applyFilters() {
        viewModelScope.launch {
            Log.d("ProductGridVM", "Evento: ApplyFilters")
            state = state.copy(isLoading = true)
            try {
                val products = productsRepository.filterProducts(state.filter)
                state = state.copy(products = products, isLoading = false)
                Log.d("ProductGridVM", "Resultado: "+products.size)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}

class ProductGridViewModelFactory(
    private val products: ProductRepository,
    private val categories: CategoryRepository,
    private val suppliers: SupplierRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductGridViewModel::class.java)) {
            return ProductGridViewModel(
                products,
                categories,
                suppliers
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
