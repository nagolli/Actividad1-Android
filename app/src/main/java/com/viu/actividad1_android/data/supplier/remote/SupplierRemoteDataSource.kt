package com.viu.actividad1_android.data.supplier.remote

import com.viu.actividad1_android.data.supplier.remote.api.SupplierApi

/**
 * Fuente de datos remota para proveedores.
 *
 * Encapsula las llamadas a la API real y expone métodos suspend
 * que devuelven los DTOs tal como llegan del backend. El mapeo
 * a modelos de dominio se realiza en el repositorio.
 *
 * Esta clase es open para permitir la creación de data sources
 * fake en pruebas o cuando el backend no está disponible.
 *
 * @param api Implementación de [SupplierApi] generada por Retrofit.
 */
open class SupplierRemoteDataSource(
    private val api: SupplierApi
) {

    /**
     * Obtiene todos los proveedores desde la API remota.
     */
    open suspend fun getAll() = api.getAllSuppliers()

    /**
     * Obtiene un proveedor concreto por su ID desde la API remota.
     *
     * @param id Identificador del proveedor.
     */
    open suspend fun getById(id: Int) = api.getSupplierById(id)
}
