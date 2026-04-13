package com.viu.actividad1_android.data.order.remote

import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto

/**
 * Fuente de datos remota para pedidos.
 *
 * Encapsula las llamadas a la API real y expone métodos suspend que devuelven
 * los DTOs tal como llegan del backend. El mapeo a modelos de dominio se realiza
 * en la capa de repositorios, manteniendo la UI desacoplada de la estructura remota.
 *
 * Esta clase es open para permitir la creación de data sources fake en pruebas
 * o cuando el backend no está disponible.
 *
 * @param api Implementación de [OrderApi] generada por Retrofit.
 */
open class OrderRemoteDataSource(
    private val api: OrderApi
) {

    /**
     * Obtiene todos los pedidos desde la API remota.
     */
    open suspend fun getAll(): List<OrderDto> = api.getAllOrders()

    /**
     * Obtiene un pedido concreto por su ID.
     *
     * @param id Identificador del pedido.
     */
    open suspend fun getById(id: Int): OrderDto = api.getOrderById(id)

    /**
     * Envía al backend la información necesaria para crear un nuevo pedido.
     *
     * @param dto Datos del pedido a crear.
     */
    open suspend fun placeOrder(dto: PlaceOrderDto): OrderDto = api.placeOrder(dto)
}
