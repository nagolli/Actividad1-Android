package com.viu.actividad1_android.data.product.remote.dto

data class ProductFilterDto(
    val name: String?,
    val category: Int?,
    val supplier: Int?,
    val min: Double?,
    val max: Double?
)
