package com.viu.actividad1_android.data.order

import com.viu.actividad1_android.data.product.Product

data class Order(
    val id: Int,
    val date: String,
    val state: String,
    val products: List<Product>
)
