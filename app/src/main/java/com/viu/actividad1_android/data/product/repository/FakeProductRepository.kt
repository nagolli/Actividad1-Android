package com.viu.actividad1_android.data.product.repository

import com.viu.actividad1_android.data.ApiConfig
import com.viu.actividad1_android.data.product.remote.FakeProductApi
import com.viu.actividad1_android.data.product.remote.ProductRemoteDataSource
import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Devuelve un ProductRemoteDataSource basado en backend real o fake,
 * dependiendo de la disponibilidad del servidor.
 */
fun productFakeOrHttpDataSource(): ProductRemoteDataSource {
    return if (ApiConfig.isBackendAvailable()) {
        val api = ApiConfig.createApi<ProductApi>()
        ProductRemoteDataSource(api)
    } else {
        FakeProductRemoteDataSource()
    }
}

/**
 * Implementación fake del remote data source usando FakeProductApi.
 * Incluye lógica de filtrado equivalente a la del backend.
 */
class FakeProductRemoteDataSource :
    ProductRemoteDataSource(api = FakeProductApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int): ProductDto =
        getAll().firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Producto con id $id no encontrado")

    override suspend fun filter(body: ProductFilterDto): List<ProductDto> {
        val products = getAll()

        return products.filter { product ->

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
