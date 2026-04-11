package com.viu.actividad1_android.data.supplier.mapper

import com.viu.actividad1_android.data.supplier.Supplier
import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto

fun SupplierDto.toDomain() = Supplier(
    id = id,
    name = name
)
