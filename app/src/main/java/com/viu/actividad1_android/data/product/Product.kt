package com.viu.actividad1_android.data.product

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String?
)