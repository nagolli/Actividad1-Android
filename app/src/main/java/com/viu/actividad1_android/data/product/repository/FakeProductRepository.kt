package com.viu.actividad1_android.data.product.repository

import com.viu.actividad1_android.data.getEndpoint
import com.viu.actividad1_android.data.isBackendAvailable
import com.viu.actividad1_android.data.product.remote.FakeProductApi
import com.viu.actividad1_android.data.product.remote.ProductRemoteDataSource
import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto


fun productFakeOrHttpDataSource(): ProductRemoteDataSource {
    return if (isBackendAvailable()) {
        val api = getEndpoint<ProductApi>()
        ProductRemoteDataSource(api)
    } else {
        FakeProductRemoteDataSource()
    }
}


class FakeProductRepository : ProductRepository(
    remote = FakeProductRemoteDataSource()
)

class FakeProductRemoteDataSource :
    ProductRemoteDataSource(api = FakeProductApi()) {

    // Si quieres, puedes sobrescribir métodos:
    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)

    override suspend fun filter(body: ProductFilterDto): List<ProductDto> {
        return getAll().filter { product ->

            val matchesName = body.name.isNullOrBlank() ||
                    product.name.contains(body.name, ignoreCase = true)

            val matchesCategory = body.category == null ||
                    product.categoryId == body.category

            val matchesSupplier = body.supplier == null ||
                    product.supplierId == body.supplier

            val matchesMin = body.min == null || product.price >= body.min
            val matchesMax = body.max == null || product.price <= body.max

            matchesName && matchesCategory && matchesSupplier && matchesMin && matchesMax
        }
    }
}
