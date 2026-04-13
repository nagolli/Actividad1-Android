package com.viu.actividad1_android.data.category.mapper

import com.viu.actividad1_android.data.category.Category
import com.viu.actividad1_android.data.category.remote.dto.CategoryDto

/**
 * Convierte un [CategoryDto] recibido desde la API remota en un modelo
 * de dominio [Category].
 *
 * Mantiene la capa de dominio desacoplada de la estructura del backend.
 */
fun CategoryDto.toDomain() = Category(
    id = id,
    name = name
)
