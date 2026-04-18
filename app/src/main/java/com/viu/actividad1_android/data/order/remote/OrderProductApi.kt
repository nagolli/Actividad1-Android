package com.viu.actividad1_android.data.order.remote

import com.viu.actividad1_android.data.order.remote.OrderRemoteDataSource
import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.OrderProductDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import com.viu.actividad1_android.data.product.repository.FakeProductRemoteDataSource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Implementación fake de [OrderApi] para entornos sin backend disponible.
 *
 * Mantiene una lista mutable de pedidos simulados y permite:
 * - obtener todos los pedidos
 * - obtener un pedido por ID
 * - crear un nuevo pedido (placeOrder)
 *
 * Útil para desarrollo offline, pruebas locales y fallback automático.
 */
class FakeOrderApi : OrderApi {

    /** Lista mutable de pedidos simulados. */
    private val mockOrders = mutableListOf(
        OrderDto(
            id = 1,
            date = "06-10-2025 13:00:00",
            state = "Entregado",
            products = listOf(
                OrderProductDto(
                    id = 5,
                    name = "Terraforming Mars",
                    quantity = 1,
                    price = 60.0
                )
            )
        ),
        OrderDto(
            id = 2,
            date = "12-01-2026 07:25:00",
            state = "En reparto",
            products = listOf(
                OrderProductDto(
                    id = 4,
                    name = "Virus!",
                    quantity = 1,
                    price = 12.0
                ),
                OrderProductDto(
                    id = 6,
                    name = "Exploding Kittens",
                    quantity = 1,
                    price = 20.0
                )
            )
        )
    )

    override suspend fun getAllOrders(): List<OrderDto> = mockOrders

    override suspend fun getOrderById(id: Int): OrderDto =
        mockOrders.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Pedido con id $id no encontrado")

    override suspend fun placeOrder(dto: PlaceOrderDto): OrderDto {
        val newId = (mockOrders.maxOfOrNull { it.id } ?: 0) + 1

        val products = FakeProductRemoteDataSource().getAll();
        val newOrder = OrderDto(
            id = newId,
            date = nowString(),
            state = "Procesando",
            products = dto.products.map { item ->
                val prod = products.find { p -> p.id == item.productId }
                    ?: throw IllegalStateException("Producto no encontrado: ${item.productId}")
                OrderProductDto(
                    id = item.productId,
                    name = prod.name,
                    quantity = item.quantity,
                    price = prod.price
                )
            }
        )

        mockOrders.add(newOrder)
        return newOrder
    }
}

/**
 * Devuelve la fecha y hora actual en formato "yyyy-MM-dd HH:mm:ss".
 */
fun nowString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.now().format(formatter)
}
