package com.viu.actividad1_android.data.supplier.remote

import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi
import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto

class FakeSupplierApi  : SupplierApi {

    private val mockSuppliers = listOf(
        SupplierDto(1, "Devir Iberia"),
        SupplierDto(2, "Asmodee España"),
        SupplierDto(3, "Tranjis Games"),
        SupplierDto(4, "Maldito Games")
    )

    override suspend fun getAllSuppliers(): List<SupplierDto> = mockSuppliers

    override suspend fun getSupplierById(id: Int): SupplierDto =
        mockSuppliers.first { it.id == id }

}
