package com.viu.actividad1_android.data.category.repository

import com.viu.actividad1_android.data.ApiConfig
import com.viu.actividad1_android.data.category.remote.FakeCategoryApi
import com.viu.actividad1_android.data.category.remote.CategoryRemoteDataSource
import com.viu.actividad1_android.data.category.remote.api.CategoryApi

/**
 * Devuelve un [CategoryRemoteDataSource] basado en si el backend está disponible.
 *
 * - Si el backend responde: usa Retrofit.
 * - Si no: usa una implementación fake.
 */
fun categoryFakeOrHttpDataSource(): CategoryRemoteDataSource {
    return if (ApiConfig.isBackendAvailable()) {
        val api = ApiConfig.createApi<CategoryApi>()
        CategoryRemoteDataSource(api)
    } else {
        FakeCategoryRemoteDataSource()
    }
}

/**
 * Fuente de datos remota fake para categorías.
 *
 * Extiende [CategoryRemoteDataSource] pero utiliza [FakeCategoryApi]
 * como backend simulado.
 */
class FakeCategoryRemoteDataSource :
    CategoryRemoteDataSource(api = FakeCategoryApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)
}
