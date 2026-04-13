package com.viu.actividad1_android.data.product.mapper

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.remote.dto.ProductDto

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
    stock = stock,
    quantity = 0,
    imageUrl = image
)
