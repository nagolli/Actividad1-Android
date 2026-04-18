package com.viu.actividad1_android.data.order

data class OrderProduct(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String? = null
)