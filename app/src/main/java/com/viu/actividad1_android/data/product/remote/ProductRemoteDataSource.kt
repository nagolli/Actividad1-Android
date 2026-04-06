package com.viu.actividad1_android.data.product.remote

import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

class ProductRemoteDataSource(
    private val api: ProductApi
) {

    suspend fun getAll() = api.getAllProducts()

    suspend fun getById(id: Int) = api.getProductById(id)

    suspend fun filter(body: ProductFilterDto) = api.filterProducts(body)

}