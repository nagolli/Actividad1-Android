package com.viu.actividad1_android.data.product.repository

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.mapper.toDomain
import com.viu.actividad1_android.data.product.remote.ProductRemoteDataSource
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

class ProductRepository(
    private val remote: ProductRemoteDataSource
) {

    suspend fun getProducts(): List<Product> =
        remote.getAll().map { it.toDomain() }

    suspend fun getProduct(id: Int): Product =
        remote.getById(id).toDomain()

    suspend fun filterProducts(filter: ProductFilterDto): List<Product> =
        remote.filter(filter).map { it.toDomain() }

}
