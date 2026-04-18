package com.viu.actividad1_android.activities.cart

data class CartUiState(
    val items: List<CartItem> = emptyList()
) {
    val totalPrice: Double
        get() = items.sumOf { it.price * it.quantity }
}