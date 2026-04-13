package com.viu.actividad1_android.data.supplier.remote

import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi
import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto

/**
 * Implementación fake de [SupplierApi] para entornos sin backend disponible.
 *
 * Proporciona una lista fija de proveedores simulados, útil para:
 * - pruebas locales
 * - desarrollo sin conexión
 * - fallback automático cuando el backend no responde
 *
 * Esta clase devuelve DTOs directamente, dejando el mapeo a dominio
 * en el repositorio correspondiente.
 */
class FakeSupplierApi : SupplierApi {

    /** Datos simulados de proveedores. */
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
