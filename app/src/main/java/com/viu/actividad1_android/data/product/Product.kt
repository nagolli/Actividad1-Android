package com.viu.actividad1_android.data.product

/**
 * Modelo de dominio que representa un producto dentro de la aplicación.
 *
 * Este modelo es independiente de la estructura del backend. Los datos
 * recibidos desde la API se transforman mediante un mapper desde ProductDto
 * antes de llegar a esta clase, garantizando que la UI y el dominio no
 * dependan de cambios en la API remota.
 *
 * @param id Identificador único del producto.
 * @param name Nombre del producto.
 * @param price Precio del producto.
 * @param stock Cantidad disponible en inventario.
 * @param imageUrl URL de la imagen del producto, si está disponible.
 */
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val imageUrl: String?
)
