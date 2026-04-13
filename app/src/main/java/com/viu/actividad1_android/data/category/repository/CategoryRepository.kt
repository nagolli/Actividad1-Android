package com.viu.actividad1_android.data.category.repository

import com.viu.actividad1_android.data.category.Category
import com.viu.actividad1_android.data.category.mapper.toDomain
import com.viu.actividad1_android.data.category.remote.CategoryRemoteDataSource

/**
 * Repositorio encargado de gestionar categorías dentro de la aplicación.
 *
 * Orquesta la obtención de datos desde la fuente remota y realiza el mapeo
 * a modelos de dominio. Mantiene la UI y la lógica de negocio desacopladas
 * de los DTOs remotos.
 *
 * @param remote Fuente de datos remota para categorías.
 */
open class CategoryRepository(
    private val remote: CategoryRemoteDataSource
) {

    /**
     * Obtiene todas las categorías en formato de dominio.
     */
    suspend fun getCategories(): List<Category> =
        remote.getAll().map { it.toDomain() }

    /**
     * Obtiene una categoría concreta por su ID en formato de dominio.
     *
     * @param id Identificador de la categoría.
     */
    suspend fun getCategory(id: Int): Category =
        remote.getById(id).toDomain()
}
