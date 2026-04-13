package com.viu.actividad1_android.data.order.remote.dto

/**
 * DTO recibido desde la API remota para representar un pedido.
 *
 * Refleja exactamente la estructura enviada por el backend. El mapeo a modelos
 * de dominio se realiza en la capa de mapeadores, manteniendo desacopladas la UI
 * y la lógica de dominio de la estructura del backend.
 *
 * @param id Identificador único del pedido.
 * @param date Fecha del pedido en formato ISO o cadena enviada por el backend.
 * @param state Estado actual del pedido.
 * @param products Lista de productos asociados al pedido.
 */
data class OrderDto(
    val id: Int,
    val date: String,
    val state: String,
    val products: List<OrderProductDto>
)

/**
 * DTO que representa un producto dentro de un pedido.
 *
 * @param id Identificador del producto.
 * @param name Nombre del producto.
 * @param quantity Cantidad solicitada.
 * @param price Precio unitario del producto en el pedido.
 */
data class OrderProductDto(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double
)
