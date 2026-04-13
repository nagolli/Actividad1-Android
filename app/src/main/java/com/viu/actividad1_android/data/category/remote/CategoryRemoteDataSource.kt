package com.viu.actividad1_android.data.category.remote

import com.viu.actividad1_android.data.category.remote.api.CategoryApi

/**
 * Fuente de datos remota para categorías.
 *
 * Encapsula las llamadas a la API real y expone métodos suspend que devuelven
 * los DTOs tal como llegan del backend. El mapeo a modelos de dominio se realiza
 * en la capa de repositorios, manteniendo la UI desacoplada de la estructura remota.
 *
 * Esta clase es open para permitir la creación de data sources fake en pruebas
 * o cuando el backend no está disponible.
 *
 * @param api Implementación de [CategoryApi] generada por Retrofit.
 */
open class CategoryRemoteDataSource(
    private val api: CategoryApi
) {

    /**
     * Obtiene todas las categorías desde la API remota.
     */
    open suspend fun getAll() = api.getAllCategories()

    /**
     * Obtiene una categoría concreta por su ID.
     *
     * @param id Identificador de la categoría.
     */
    open suspend fun getById(id: Int) = api.getCategoryById(id)
}
