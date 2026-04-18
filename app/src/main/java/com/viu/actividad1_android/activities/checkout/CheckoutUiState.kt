package com.viu.actividad1_android.activities.checkout

import com.viu.actividad1_android.activities.cart.CartItem

data class CheckoutUiState(
    val email: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val telefono: String = "",
    val codigoPostal: String = "",
    val direccion: String = "",
    val poblacion: String = "",
    val errors: Map<String, String> = emptyMap(),
    // Añadir Carrito
    val cartItems: List<CartItem> = emptyList(),
    val isLoading: Boolean = false
)