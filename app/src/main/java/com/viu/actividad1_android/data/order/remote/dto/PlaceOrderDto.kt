package com.viu.actividad1_android.data.order.remote.dto

data class PlaceOrderDto(
    val email: String,
    val addressId: Int,
    val products: List<PlaceOrderProductDto>
)

data class PlaceOrderProductDto(
    val productId: Int,
    val quantity: Int
)