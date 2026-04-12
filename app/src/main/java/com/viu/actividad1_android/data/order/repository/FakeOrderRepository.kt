package com.viu.actividad1_android.data.order.repository

import com.viu.actividad1_android.data.getEndpoint
import com.viu.actividad1_android.data.isBackendAvailable
import com.viu.actividad1_android.data.order.remote.FakeOrderApi
import com.viu.actividad1_android.data.order.remote.OrderRemoteDataSource
import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto


fun orderFakeOrHttpDataSource(): OrderRemoteDataSource {
    return if (isBackendAvailable()) {
        val api = getEndpoint<OrderApi>()
        OrderRemoteDataSource(api)
    } else {
        FakeOrderRemoteDataSource()
    }
}


class FakeOrderRepository : OrderRepository(
    remote = FakeOrderRemoteDataSource()
)

class FakeOrderRemoteDataSource :
    OrderRemoteDataSource(api = FakeOrderApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)

    override suspend fun placeOrder(dto: PlaceOrderDto): OrderDto {
        return super.placeOrder(dto)
    }

}
