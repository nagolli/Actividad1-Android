package com.viu.actividad1_android.activities.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.viu.actividad1_android.data.product.Product

class CartViewModel : ViewModel() {

    private val _state = mutableStateOf(CartUiState())
    val state = _state

    init {
        loadCart()
    }

    private fun loadCart() {
        _state.value = CartUiState(items = emptyList())
    }

    fun addItem(item: CartItem) {
        val currentItems = _state.value.items.toMutableList()

        val existingItem = currentItems.find { it.id == item.id }

        if (existingItem != null) {
            // aumentar cantidad
            val updated = existingItem.copy(
                quantity = existingItem.quantity + item.quantity
            )

            currentItems.remove(existingItem)
            currentItems.add(updated)
        } else {
            currentItems.add(item)
        }

        _state.value = _state.value.copy(items = currentItems)
    }

    fun removeItem(itemId: Int) {
        _state.value = _state.value.copy(
            items = _state.value.items.filter { it.id != itemId }
        )
    }

    fun clearCart() {
        _state.value = CartUiState(emptyList())
    }
}