package com.viu.actividad1_android.data.supplier.remote.api

import com.viu.actividad1_android.data.supplier.remote.dto.SupplierDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz Retrofit para acceder a los endpoints remotos relacionados
 * con proveedores.
 *
 * Esta interfaz define las rutas expuestas por el backend y devuelve
 * DTOs que posteriormente serán mapeados a modelos de dominio en la
 * capa de repositorios.
 */
interface SupplierApi {

    /**
     * Obtiene la lista completa de proveedores desde el backend.
     *
     * @return Lista de [SupplierDto] recibida de la API.
     */
    @GET("supplier")
    suspend fun getAllSuppliers(): List<SupplierDto>

    /**
     * Obtiene un proveedor concreto por su ID.
     *
     * @param id Identificador del proveedor.
     * @return Un [SupplierDto] correspondiente al proveedor solicitado.
     */
    @GET("supplier/{id}")
    suspend fun getSupplierById(@Path("id") id: Int): SupplierDto
}
