package com.viu.actividad1_android.data.supplier.remote.dto

/**
 * DTO recibido desde la API remota para representar un proveedor.
 *
 * Este modelo refleja exactamente la estructura enviada por el backend.
 * El mapeo a modelos de dominio se realiza mediante la función `toDomain()`
 * en la capa de mapeadores.
 *
 * @param id Identificador único del proveedor.
 * @param name Nombre del proveedor.
 */
data class SupplierDto(
    val id: Int,
    val name: String
)
