package com.viu.actividad1_android.data.category.remote.dto

/**
 * DTO recibido desde la API remota para representar una categoría.
 *
 * Refleja exactamente la estructura enviada por el backend. El mapeo a modelos
 * de dominio se realiza en la capa de mapeadores, manteniendo desacopladas la UI
 * y la lógica de dominio de la estructura del backend.
 *
 * @param id Identificador único de la categoría.
 * @param name Nombre de la categoría.
 */
data class CategoryDto(
    val id: Int,
    val name: String
)
