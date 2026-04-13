package com.viu.actividad1_android.data.order.remote.api

import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Interfaz Retrofit para acceder a los endpoints remotos relacionados
 * con pedidos.
 *
 * Define las rutas expuestas por el backend y devuelve DTOs que serán
 * posteriormente mapeados a modelos de dominio en la capa de repositorios.
 */
interface OrderApi {

    /**
     * Obtiene la lista completa de pedidos desde el backend.
     */
    @GET("order")
    suspend fun getAllOrders(): List<OrderDto>

    /**
     * Obtiene un pedido concreto por su ID.
     *
     * @param id Identificador del pedido.
     */
    @GET("order/{id}")
    suspend fun getOrderById(@Path("id") id: Int): OrderDto

    /**
     * Envía al backend la información necesaria para crear un nuevo pedido.
     *
     * @param dto Datos del pedido a crear.
     */
    @POST("order")
    suspend fun placeOrder(@Body dto: PlaceOrderDto): OrderDto
}
