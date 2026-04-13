package com.viu.actividad1_android.data.order

import com.viu.actividad1_android.data.product.Product

/**
 * Modelo de dominio que representa un pedido dentro de la aplicación.
 *
 * Este modelo es independiente de la estructura del backend. Los datos
 * recibidos desde la API se transforman mediante mapeadores antes de llegar
 * a esta clase, garantizando que la UI y la lógica de dominio permanezcan
 * desacopladas de los DTOs remotos.
 *
 * @param id Identificador único del pedido.
 * @param date Fecha del pedido en formato de cadena.
 * @param state Estado actual del pedido.
 * @param products Lista de productos asociados al pedido en formato de dominio.
 * @property totalPrice Suma de precios de los productos.
 */
data class Order(
    val id: Int,
    val date: String,
    val state: String,
    val products: List<Product>
) {
    fun totalPrice(): Double {
        return products.sumOf { it.price * it.quantity }
    }

}
