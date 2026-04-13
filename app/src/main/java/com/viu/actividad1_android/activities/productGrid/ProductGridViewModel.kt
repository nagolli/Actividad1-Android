package com.viu.actividad1_android.activities.productGrid

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.data.category.repository.CategoryRepository
import com.viu.actividad1_android.data.product.repository.ProductRepository
import com.viu.actividad1_android.data.supplier.repository.SupplierRepository
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla Product Grid.
 *
 * Gestiona el estado [ProductGridState], procesa los eventos [ProductGridEvent]
 * y coordina la carga de datos desde los repositorios siguiendo el patrón MVVM.
 *
 * @param productsRepository Repositorio de productos.
 * @param categoryRepository Repositorio de categorías.
 * @param supplierRepository Repositorio de proveedores.
 */
class ProductGridViewModel(
    private val productsRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val supplierRepository: SupplierRepository
) : ViewModel() {

    /** Estado observable de la pantalla. */
    var state by mutableStateOf(ProductGridState())
        private set

    init {
        onEvent(ProductGridEvent.LoadProducts)
        onEvent(ProductGridEvent.LoadCategories)
        onEvent(ProductGridEvent.LoadSuppliers)
    }

    /**
     * Procesa los eventos enviados desde la UI.
     */
    fun onEvent(event: ProductGridEvent) {
        when (event) {

            ProductGridEvent.LoadProducts -> loadProducts()

            ProductGridEvent.LoadCategories -> loadCategories()

            ProductGridEvent.LoadSuppliers -> loadSuppliers()

            is ProductGridEvent.OnFilterChanged -> {
                state = state.copy(filter = event.filter)
                applyFilters()
            }

            ProductGridEvent.ApplyFilters -> applyFilters()

            is ProductGridEvent.OnProductClick -> {
                // La navegación se gestiona desde la UI.
                Log.d("ProductGridVM", "Producto seleccionado: ${event.id}")
            }
        }
    }

    /**
     * Carga la lista completa de productos desde el repositorio.
     */
    private fun loadProducts() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val products = productsRepository.getProducts()
                val maxPrice = products.maxOfOrNull { it.price }?.toFloat() ?: 0f

                state = state.copy(
                    products = products,
                    maxPrice = maxPrice,
                    isLoading = false
                )

                Log.d("ProductGridVM", "Productos cargados: ${products.size}")

            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
                Log.e("ProductGridVM", "Error cargando productos", e)
            }
        }
    }

    /**
     * Carga las categorías disponibles.
     */
    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val categories = categoryRepository.getCategories()
                state = state.copy(
                    categories = categories.map { it.id to it.name }
                )

                Log.d("ProductGridVM", "Categorías cargadas: ${categories.size}")

            } catch (e: Exception) {
                state = state.copy(error = e.message)
                Log.e("ProductGridVM", "Error cargando categorías", e)
            }
        }
    }

    /**
     * Carga los proveedores disponibles.
     */
    private fun loadSuppliers() {
        viewModelScope.launch {
            try {
                val suppliers = supplierRepository.getSuppliers()
                state = state.copy(
                    suppliers = suppliers.map { it.id to it.name }
                )

                Log.d("ProductGridVM", "Proveedores cargados: ${suppliers.size}")

            } catch (e: Exception) {
                state = state.copy(error = e.message)
                Log.e("ProductGridVM", "Error cargando proveedores", e)
            }
        }
    }

    /**
     * Aplica los filtros seleccionados por el usuario.
     */
    private fun applyFilters() {
        viewModelScope.launch {
            Log.d("ProductGridVM", "Aplicando filtros: ${state.filter}")

            state = state.copy(isLoading = true)

            try {
                val filteredProducts = productsRepository.filterProducts(state.filter)

                state = state.copy(
                    products = filteredProducts,
                    isLoading = false
                )

                Log.d("ProductGridVM", "Productos filtrados: ${filteredProducts.size}")

            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
                Log.e("ProductGridVM", "Error aplicando filtros", e)
            }
        }
    }
}

/**
 * Factory para crear instancias de [ProductGridViewModel] con parámetros.
 */
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
