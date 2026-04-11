package com.viu.actividad1_android.data.supplier.remote

import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi

open class SupplierRemoteDataSource(
    private val api: SupplierApi
) {

    open suspend fun getAll() = api.getAllSuppliers()

    open suspend fun getById(id: Int) = api.getSupplierById(id)

}