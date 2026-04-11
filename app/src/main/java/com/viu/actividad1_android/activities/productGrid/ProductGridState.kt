package com.viu.actividad1_android.activities.productGrid

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

//Definicion de estados de la vista de productos (Y valores iniciales y constantes)
data class ProductGridState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val filter: ProductFilterDto = ProductFilterDto(
        name = null,
        category = null,
        supplier = null,
        min = null,
        max = null,
    ),
    val categories: List<Pair<Int,String>> = emptyList(),
    val suppliers: List<Pair<Int,String>> = emptyList(),
    val maxPrice: Float = 0f
)
