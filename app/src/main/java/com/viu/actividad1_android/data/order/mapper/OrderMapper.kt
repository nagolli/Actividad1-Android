package com.viu.actividad1_android.data.order.mapper

import com.viu.actividad1_android.data.order.Order
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.OrderProductDto
import com.viu.actividad1_android.data.product.Product
import kotlin.Int
import kotlin.String

fun OrderDto.toDomain(currentProducts: List<Product>) = Order(
    id = id,
    date = date,
    state = state,
    products = products.map { dto ->
        val localProduct = currentProducts.firstOrNull { it.id == dto.id }
        dto.toDomainProduct(localProduct)
    }
)

fun OrderProductDto.toDomainProduct(currentProduct: Product?) = Product(
    id = id,
    name = name,
    price = price,
    stock = currentProduct?.stock ?: 0,
    imageUrl = currentProduct?.imageUrl
)