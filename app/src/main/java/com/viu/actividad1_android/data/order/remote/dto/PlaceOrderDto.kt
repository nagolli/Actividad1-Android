package com.viu.actividad1_android.data.order.remote.dto

/**
 * DTO utilizado para enviar al backend la información necesaria
 * para realizar un pedido.
 *
 * Representa la estructura exacta esperada por la API al crear un pedido.
 * El repositorio se encarga de construir este objeto a partir de los datos
 * de dominio antes de enviarlo.
 *
 * @param email Email del usuario que realiza el pedido.
 * @param addressId Identificador de la dirección seleccionada para el envío.
 * @param products Lista de productos incluidos en el pedido.
 */
data class PlaceOrderDto(
    val email: String,
    val addressId: Int,
    val products: List<PlaceOrderProductDto>
)

/**
 * DTO que representa un producto dentro de la solicitud de creación de pedido.
 *
 * @param productId Identificador del producto.
 * @param quantity Cantidad solicitada del producto.
 */
data class PlaceOrderProductDto(
    val productId: Int,
    val quantity: Int
)
