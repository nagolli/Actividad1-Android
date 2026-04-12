package com.viu.actividad1_android.data.order.remote.api

import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface OrderApi {

    @GET("order")
    suspend fun getAllOrders(): List<OrderDto>

    @GET("order/{id}")
    suspend fun getOrderById(@Path("id") id: Int): OrderDto

    @POST("order")
    suspend fun placeOrder(@Body dto: PlaceOrderDto): OrderDto
}
