package com.viu.actividad1_android.data.product.remote

import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

open class ProductRemoteDataSource(
    private val api: ProductApi
) {

    open suspend fun getAll() = api.getAllProducts()

    open suspend fun getById(id: Int) = api.getProductById(id)

    open suspend fun filter(body: ProductFilterDto) = api.filterProducts(body)

}