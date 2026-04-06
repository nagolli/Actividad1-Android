package com.viu.actividad1_android.data.product.mapper

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.remote.dto.ProductDto

fun ProductDto.toDomain() = Product(
    id = id,
    name = name,
    price = price,
    stock = stock,
    imageUrl = image
)
