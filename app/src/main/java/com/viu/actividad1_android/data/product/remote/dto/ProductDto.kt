package com.viu.actividad1_android.data.product.remote.dto

/**
 * DTO recibido desde la API remota para representar un producto.
 *
 * Refleja exactamente la estructura enviada por el backend. El mapeo a modelos
 * de dominio se realiza mediante la función `toDomain()` en la capa de mapeadores,
 * garantizando que la UI y el dominio permanezcan desacoplados de la API.
 *
 * @param id Identificador único del producto.
 * @param name Nombre del producto.
 * @param price Precio del producto.
 * @param description Descripción opcional del producto.
 * @param stock Cantidad disponible en inventario.
 * @param image URL de la imagen del producto, si está disponible.
 * @param inactive Indica si el producto está marcado como inactivo.
 * @param categoryId Identificador de la categoría asociada.
 * @param supplierId Identificador del proveedor asociado.
 */
data class ProductDto(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String?,
    val stock: Int,
    val image: String?,
    val inactive: Boolean,
    val categoryId: Int,
    val supplierId: Int
)
