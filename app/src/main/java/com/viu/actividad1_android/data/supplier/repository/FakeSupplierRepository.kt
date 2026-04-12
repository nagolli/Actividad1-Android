package com.viu.actividad1_android.data.supplier.repository

import com.viu.actividad1_android.data.getEndpoint
import com.viu.actividad1_android.data.isBackendAvailable
import com.viu.actividad1_android.data.supplier.remote.FakeSupplierApi
import com.viu.actividad1_android.data.supplier.remote.SupplierRemoteDataSource
import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi


fun supplierFakeOrHttpDataSource(): SupplierRemoteDataSource {
    return if (isBackendAvailable()) {
        val api = getEndpoint<SupplierApi>()
        SupplierRemoteDataSource(api)
    } else {
        FakeSupplierRemoteDataSource()
    }
}


class FakeSupplierRepository : SupplierRepository(
    remote = FakeSupplierRemoteDataSource()
)

class FakeSupplierRemoteDataSource :
    SupplierRemoteDataSource(api = FakeSupplierApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)

}
