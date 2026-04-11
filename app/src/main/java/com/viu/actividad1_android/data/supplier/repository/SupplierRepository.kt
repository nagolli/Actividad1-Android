package com.viu.actividad1_android.data.supplier.repository

import com.viu.actividad1_android.data.supplier.Supplier
import com.viu.actividad1_android.data.supplier.mapper.toDomain
import com.viu.actividad1_android.data.supplier.remote.SupplierRemoteDataSource

open class SupplierRepository(
    private val remote: SupplierRemoteDataSource
) {

    suspend fun getSuppliers(): List<Supplier> =
        remote.getAll().map { it.toDomain() }

    suspend fun getSupplier(id: Int): Supplier =
        remote.getById(id).toDomain()

}
