package com.viu.actividad1_android.data.order.remote.dto

data class OrderDto(
    val id: Int,
    val date: String,
    val state: String,
    val products: List<OrderProductDto>
)

data class OrderProductDto(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double
)