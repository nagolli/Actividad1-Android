package com.viu.actividad1_android.data.supplier.repository

import com.viu.actividad1_android.data.supplier.Supplier
import com.viu.actividad1_android.data.supplier.mapper.toDomain
import com.viu.actividad1_android.data.supplier.remote.SupplierRemoteDataSource

/**
 * Repositorio encargado de gestionar el acceso a los datos de proveedores.
 *
 * Actúa como capa intermedia entre la fuente de datos remota y el dominio,
 * aplicando los mapeos necesarios y exponiendo métodos seguros para la UI
 * y los ViewModels siguiendo el patrón MVVM.
 *
 * @param remote Fuente de datos remota que implementa las operaciones CRUD.
 */
open class SupplierRepository(
    private val remote: SupplierRemoteDataSource
) {

    /**
     * Obtiene la lista completa de proveedores.
     *
     * @return Lista de proveedores en formato de dominio.
     */
    suspend fun getSuppliers(): List<Supplier> =
        remote.getAll().map { it.toDomain() }

    /**
     * Obtiene un proveedor concreto por su ID.
     *
     * @param id Identificador del proveedor.
     * @return Proveedor en formato de dominio.
     */
    suspend fun getSupplier(id: Int): Supplier =
        remote.getById(id).toDomain()
}
