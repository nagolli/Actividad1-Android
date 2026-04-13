package com.viu.actividad1_android.data.supplier.repository

import com.viu.actividad1_android.data.ApiConfig
import com.viu.actividad1_android.data.supplier.remote.FakeSupplierApi
import com.viu.actividad1_android.data.supplier.remote.SupplierRemoteDataSource
import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi

/**
 * Devuelve un SupplierRemoteDataSource basado en backend real o fake,
 * dependiendo de la disponibilidad del servidor.
 */
fun supplierFakeOrHttpDataSource(): SupplierRemoteDataSource {
    return if (ApiConfig.isBackendAvailable()) {
        val api = ApiConfig.createApi<SupplierApi>()
        SupplierRemoteDataSource(api)
    } else {
        FakeSupplierRemoteDataSource()
    }
}

/**
 * Implementación fake del remote data source usando FakeSupplierApi.
 */
class FakeSupplierRemoteDataSource :
    SupplierRemoteDataSource(api = FakeSupplierApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)
}
