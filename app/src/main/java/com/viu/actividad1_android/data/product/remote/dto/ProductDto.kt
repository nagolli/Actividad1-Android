package com.viu.actividad1_android.data.product.remote.dto

data class ProductDto(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String?,
    val stock: Int,
    val image: String?,
    val inactive: Boolean,
    val categoryId: Int,
    val supplierId: Int
)
