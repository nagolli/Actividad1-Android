package com.viu.actividad1_android.data.order.repository

import com.viu.actividad1_android.data.order.Order
import com.viu.actividad1_android.data.order.mapper.toDomain
import com.viu.actividad1_android.data.order.remote.OrderRemoteDataSource
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderProductDto
import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.repository.ProductRepository
import com.viu.actividad1_android.data.product.repository.productFakeOrHttpDataSource

open class OrderRepository(
    private val remote: OrderRemoteDataSource
) {

    suspend fun getOrders(): List<Order> {
        var products = ProductRepository(
            remote = productFakeOrHttpDataSource()
        ).getProducts()
        return remote.getAll().map { it.toDomain(products) }
    }

    suspend fun getOrder(id: Int): Order {
        var products = ProductRepository(
            remote = productFakeOrHttpDataSource()
        ).getProducts()
        return remote.getById(id).toDomain(products)
    }

    //Mockeamos email y address porque no tenemos usuarios en la app todavia
    suspend fun placeOrder(
        products: List<Pair<Int, Product>>, //Par cantidad/producto
        email: String = "admin@admin.example.com",
        addressId: Int = 1,
    ): Order {
        val dto = PlaceOrderDto(
            email = email,
            addressId = addressId,
            products = products.map {
                PlaceOrderProductDto(
                    productId = it.second.id,
                    quantity = it.first
                )
            }
        )

        val createdOrderDto = remote.placeOrder(dto)
        return createdOrderDto.toDomain(products.map { it.second })
    }
}
