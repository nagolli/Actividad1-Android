package com.viu.actividad1_android.data.product.remote.dto

/**
 * DTO utilizado para enviar criterios de filtrado al backend.
 *
 * Representa los parámetros opcionales que pueden aplicarse al buscar productos.
 * El backend devolverá únicamente aquellos productos que cumplan las condiciones
 * especificadas. El repositorio se encarga de mapear los resultados a modelos
 * de dominio.
 *
 * @param name Nombre o parte del nombre del producto.
 * @param category Identificador de la categoría a la que debe pertenecer.
 * @param supplier Identificador del proveedor asociado.
 * @param min Precio mínimo permitido.
 * @param max Precio máximo permitido.
 */
data class ProductFilterDto(
    val name: String?,
    val category: Int?,
    val supplier: Int?,
    val min: Double?,
    val max: Double?
)
