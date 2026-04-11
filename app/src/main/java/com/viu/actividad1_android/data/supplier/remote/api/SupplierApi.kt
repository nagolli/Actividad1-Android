package com.viu.actividad1_android.data.supplier.remote.api

import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto

import retrofit2.http.GET
import retrofit2.http.Path


interface SupplierApi {

    @GET("supplier")
    suspend fun getAllSuppliers(): List<SupplierDto>

    @GET("supplier/{id}")
    suspend fun getSupplierById(@Path("id") id: Int): SupplierDto
}
