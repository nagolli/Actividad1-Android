package com.viu.actividad1_android.data.order.mapper

import com.viu.actividad1_android.data.order.Order
import com.viu.actividad1_android.data.order.OrderProduct
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.OrderProductDto
import com.viu.actividad1_android.data.product.Product

/**
 * Convierte un OrderDto en un Order de dominio.
 *
 * @param currentProducts Lista de productos locales (catálogo completo).
 */
fun OrderDto.toDomain(): Order {
    return Order(
        id = id,
        date = date,
        state = state,
        products = products.map {
            OrderProduct(
                id = it.id,
                name = it.name,
                quantity = it.quantity,
                price = it.price
            )
        }
    )
}

/**
 * Convierte un OrderProductDto en un Product de dominio.
 *
 * Si el producto existe en el catálogo local, se reutilizan sus datos
 * (stock, imagen, etc.). Si no existe, se crea un producto mínimo.
 */
fun OrderProductDto.toDomainProduct(localProduct: Product?) = Product(
    id = id,
    name = name,
    price = price,
    stock = localProduct?.stock ?: 0,
    quantity = quantity,
    description = localProduct?.description,
    imageUrl = localProduct?.imageUrl
)
