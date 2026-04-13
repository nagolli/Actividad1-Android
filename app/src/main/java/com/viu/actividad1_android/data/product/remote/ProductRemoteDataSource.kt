package com.viu.actividad1_android.data.product.remote

import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Fuente de datos remota para productos.
 *
 * Encapsula las llamadas a la API real y expone métodos suspend
 * que devuelven los DTOs tal como llegan del backend. El mapeo
 * a modelos de dominio se realiza en la capa de repositorios.
 *
 * Esta clase es open para permitir la creación de data sources
 * fake en pruebas o cuando el backend no está disponible.
 *
 * @param api Implementación de [ProductApi] generada por Retrofit.
 */
open class ProductRemoteDataSource(
    private val api: ProductApi
) {

    /**
     * Obtiene todos los productos desde la API remota.
     */
    open suspend fun getAll() = api.getAllProducts()

    /**
     * Obtiene un producto concreto por su ID desde la API remota.
     *
     * @param id Identificador del producto.
     */
    open suspend fun getById(id: Int) = api.getProductById(id)

    /**
     * Filtra productos según los criterios especificados.
     *
     * @param body Objeto con los criterios de filtrado.
     */
    open suspend fun filter(body: ProductFilterDto) = api.filterProducts(body)
}
