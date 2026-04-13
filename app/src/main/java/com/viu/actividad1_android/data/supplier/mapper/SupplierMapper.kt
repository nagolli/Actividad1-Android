package com.viu.actividad1_android.data.supplier.mapper

import com.viu.actividad1_android.data.supplier.Supplier
import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto

/**
 * Convierte un [SupplierDto] recibido desde la API remota en un modelo
 * de dominio [Supplier] utilizado por la aplicación.
 *
 * Este mapeo garantiza que la capa de dominio no dependa de la estructura
 * exacta del backend, permitiendo cambios en la API sin afectar a la UI.
 */
fun SupplierDto.toDomain() = Supplier(
    id = id,
    name = name
)
