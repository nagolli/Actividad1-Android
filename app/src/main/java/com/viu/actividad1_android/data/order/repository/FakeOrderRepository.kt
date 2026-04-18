package com.viu.actividad1_android.data.order.repository

import com.viu.actividad1_android.data.ApiConfig
import com.viu.actividad1_android.data.order.remote.FakeOrderApi
import com.viu.actividad1_android.data.order.remote.OrderRemoteDataSource
import com.viu.actividad1_android.data.order.remote.api.OrderApi
import com.viu.actividad1_android.data.order.remote.dto.OrderDto
import com.viu.actividad1_android.data.order.remote.dto.PlaceOrderDto

/**
 * Devuelve un [OrderRemoteDataSource] basado en si el backend está disponible.
 *
 * - Si el backend responde: usa Retrofit.
 * - Si no: usa una implementación fake.
 */

fun orderFakeOrHttpDataSource(): OrderRemoteDataSource {
    return if (ApiConfig.isBackendAvailable()) {
        val api = ApiConfig.createApi<OrderApi>()
        OrderRemoteDataSource(api)
    } else {
        OrderRemoteDataSource(OrderApiProvider.fakeApi)
    }
}

object OrderApiProvider {
    val fakeApi = FakeOrderApi()
}


/**
 * Fuente de datos remota fake para pedidos.
 *
 * Extiende [OrderRemoteDataSource] pero utiliza [FakeOrderApi] como backend simulado.
 */
class FakeOrderRemoteDataSource :
    OrderRemoteDataSource(api = FakeOrderApi()) {

    override suspend fun getAll(): List<OrderDto> =
        super.getAll()

    override suspend fun getById(id: Int): OrderDto =
        super.getById(id)

    override suspend fun placeOrder(dto: PlaceOrderDto): OrderDto =
        super.placeOrder(dto)
}
