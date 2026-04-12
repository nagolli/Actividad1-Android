package com.viu.actividad1_android.data.order.remote

import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.OrderProductDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.Int
import kotlin.String
import kotlin.time.Clock.System.now

class FakeOrderApi  : OrderApi {

    private val mockOrders = mutableListOf(
        OrderDto(1, "06-10-2025 13:00:00", "Entregado", listOf(
            OrderProductDto(
                5,
                "Terraforming Mars",
                1,
                60.0
            )
        )),
        OrderDto(2, "12-01-2026 07:25:00", "En reparto",listOf(
            OrderProductDto(
                4,
                "Virus!",
                1,
                12.0
            ),
            OrderProductDto(
                6,
                "Exploding Kittens",
                1,
                20.0
            )
        ))
    )

    override suspend fun getAllOrders(): List<OrderDto> = mockOrders

    override suspend fun getOrderById(id: Int): OrderDto =
        mockOrders.first { it.id == id }

    override suspend fun placeOrder(dto: PlaceOrderDto): OrderDto {
        val newId = (mockOrders.maxOfOrNull { it.id } ?: 0) + 1

        val newOrder = OrderDto(
            id = newId,
            date = nowString(),
            state = "Procesando",
            products = dto.products.map {
                OrderProductDto(
                    id = it.productId,
                    name = "Producto $it.productId (fake)",
                    quantity = it.quantity,
                    price = 9.99 // precio fake
                )
            }
        )

        mockOrders.add(newOrder)
        return newOrder
    }
}

fun nowString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.now().format(formatter)
}