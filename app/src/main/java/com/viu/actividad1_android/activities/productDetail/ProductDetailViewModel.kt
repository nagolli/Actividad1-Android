package com.viu.actividad1_android.activities.productDetail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.viu.actividad1_android.activities.cart.CartItem
import com.viu.actividad1_android.activities.cart.CartViewModel
import com.viu.actividad1_android.data.product.repository.ProductRepository
import kotlinx.coroutines.launch

sealed class ProductDetailEvent {
    data class OnQuantityChanged(val quantity: Int) : ProductDetailEvent()
    object OnAddToCart : ProductDetailEvent()
}

/**
 * ViewModel para la pantalla de detalles de producto.
 *
 * Se encarga de cargar la información del producto seleccionado desde el
 * repositorio y exponerla a la UI mediante un estado reactivo.
 *
 * @param productRepository Repositorio de productos.
 * @param productId Identificador del producto a cargar.
 */
class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val productId: Int,
    private val cartViewModel: CartViewModel
) : ViewModel() {

    var state by mutableStateOf(ProductDetailState())
        private set

    var message = mutableStateOf<String?>(null)
        private set

    init {
        loadProduct()
    }

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.OnQuantityChanged -> {
                if (event.quantity >= 1) {
                    state = state.copy(quantity = event.quantity)
                }
            }
            is ProductDetailEvent.OnAddToCart -> {
                val product = state.product ?: return
                Log.d("ProductDetailViewModel", "Añadido al carrito: ${product.name}")
                cartViewModel.addItem(
                    CartItem(
                        id = product.id,
                        name = product.name,
                        price = product.price,
                        quantity = state.quantity
                    )
                )
                message.value = "${product.name} añadido al carrito"
            }
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            try {
                val product = productRepository.getProduct(productId)
                val rating = try {
                    productRepository.getProductAverageRating(productId)
                } catch (e: Exception) {
                    null
                }
                val reviews = try {
                    productRepository.getProductReviews(productId)
                } catch (e: Exception) {
                    emptyList()
                }
                state = state.copy(
                    isLoading = false,
                    product = product,
                    averageRating = rating,
                    reviews = reviews
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message ?: "Error al cargar el producto"
                )
            }
        }
    }
}

/**
 * Factory para instanciar el ViewModel con parámetros.
 */
class ProductDetailViewModelFactory(
    private val products: ProductRepository,
    private val productId: Int,
    private val cartViewModel: CartViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(products, productId, cartViewModel) as T
    }
}
