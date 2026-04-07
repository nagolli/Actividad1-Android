package com.viu.actividad1_android.data.product.repository

import com.viu.actividad1_android.data.product.remote.FakeProductApi
import com.viu.actividad1_android.data.product.remote.ProductRemoteDataSource
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto


class FakeProductRepository : ProductRepository(
    remote = FakeProductRemoteDataSource()
)

class FakeProductRemoteDataSource :
    ProductRemoteDataSource(api = FakeProductApi()) {

    // Si quieres, puedes sobrescribir métodos:
    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)

    override suspend fun filter(body: ProductFilterDto) = super.filter(body)
}
