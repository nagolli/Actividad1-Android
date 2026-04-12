package com.viu.actividad1_android.data.order.remote

import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto

open class OrderRemoteDataSource(
    private val api: OrderApi
) {

    open suspend fun getAll() = api.getAllOrders()

    open suspend fun getById(id: Int) = api.getOrderById(id)
    
    open suspend fun placeOrder(dto: PlaceOrderDto) = api.placeOrder(dto)
}