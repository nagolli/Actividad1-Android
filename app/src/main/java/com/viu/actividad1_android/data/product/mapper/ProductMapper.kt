package com.viu.actividad1_android.data.product.mapper

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.Review
import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ReviewDto

/**
 * Convierte un [ProductDto] recibido desde la API remota en un modelo
 * de dominio [Product] utilizado por la aplicación.
 *
 * Este mapeo desacopla la capa de dominio de la estructura del backend,
 * permitiendo modificar el DTO sin afectar a la UI.
 */
fun ProductDto.toDomain() = Product(
    id = id,
    name = name,
    price = price,
    description = description,
    stock = stock,
    quantity = 0,
    imageUrl = image
)

/**
 * Convierte un DTO de reseña en un modelo de dominio.
 */
fun ReviewDto.toDomain(): Review {
    return Review(
        productId = productId,
        rating = rating,
        review = review,
        userName = user.name,
        userEmail = user.email
    )
}
