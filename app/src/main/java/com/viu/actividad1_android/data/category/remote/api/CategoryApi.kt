package com.viu.actividad1_android.data.category.remote.api

import com.viu.actividad1_android.data.category.remote.dto.CategoryDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz Retrofit para acceder a los endpoints remotos relacionados
 * con categorías.
 *
 * Define las rutas expuestas por el backend y devuelve DTOs que luego
 * serán mapeados a modelos de dominio en la capa de mapeadores.
 */
interface CategoryApi {

    /**
     * Obtiene todas las categorías disponibles en el backend.
     */
    @GET("category")
    suspend fun getAllCategories(): List<CategoryDto>

    /**
     * Obtiene una categoría concreta por su ID.
     *
     * @param id Identificador de la categoría.
     */
    @GET("category/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): CategoryDto
}
