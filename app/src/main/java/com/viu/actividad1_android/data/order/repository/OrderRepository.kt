package com.viu.actividad1_android.data.order.repository

import com.viu.actividad1_android.activities.cart.CartItem
import com.viu.actividad1_android.data.order.Order
import com.viu.actividad1_android.data.order.mapper.toDomain
import com.viu.actividad1_android.data.order.remote.OrderRemoteDataSource
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderProductDto
import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.repository.ProductRepository
import com.viu.actividad1_android.data.product.repository.productFakeOrHttpDataSource

/**
 * Repositorio encargado de gestionar pedidos dentro de la aplicación.
 *
 * Orquesta la obtención de pedidos desde la fuente remota y realiza el mapeo
 * a modelos de dominio. También construye los DTO necesarios para enviar
 * pedidos al backend.
 *
 * @param remote Fuente de datos remota para pedidos.
 */
open class OrderRepository(
    private val remote: OrderRemoteDataSource
) {

    /**
     * Obtiene todos los pedidos en formato de dominio.
     *
     * Para mapear correctamente los productos incluidos en cada pedido,
     * se obtiene la lista completa de productos desde el repositorio de productos.
     */
    suspend fun getOrders(): List<Order> {
        val products = ProductRepository(
            remote = productFakeOrHttpDataSource()
        ).getProducts()

        return remote.getAll().map { it.toDomain() }
    }

    /**
     * Obtiene un pedido concreto por su ID en formato de dominio.
     *
     * @param id Identificador del pedido.
     */
    suspend fun getOrder(id: Int): Order {
        val products = ProductRepository(
            remote = productFakeOrHttpDataSource()
        ).getProducts()

        return remote.getById(id).toDomain()
    }

    /**
     * Crea un nuevo pedido en el backend.
     *
     * @param products Lista de pares (cantidad, producto) seleccionados.
     * @param email Email del usuario. Mockeado hasta que exista sistema de usuarios.
     * @param addressId Dirección seleccionada. Mockeada por la misma razón.
     */
    suspend fun placeOrder(
        products: List<CartItem>,
        email: String = "admin@admin.example.com",
        addressId: Int = 1,
    ): Order {

        val dto = PlaceOrderDto(
            email = email,
            addressId = addressId,
            products = products.map {
                PlaceOrderProductDto(
                    productId = it.id,
                    quantity = it.quantity
                )
            }
        )

        val createdOrderDto = remote.placeOrder(dto)

        return createdOrderDto.toDomain()
    }
}
