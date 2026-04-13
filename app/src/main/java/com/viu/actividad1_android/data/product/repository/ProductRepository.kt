package com.viu.actividad1_android.data.product.repository

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.Review
import com.viu.actividad1_android.data.product.mapper.toDomain
import com.viu.actividad1_android.data.product.remote.ProductRemoteDataSource
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Repositorio encargado de gestionar el acceso a los datos de productos.
 *
 * Actúa como capa intermedia entre la fuente de datos remota y el dominio,
 * aplicando los mapeos necesarios y exponiendo métodos seguros para la UI
 * y los ViewModels siguiendo el patrón MVVM.
 *
 * @param remote Fuente de datos remota que implementa las operaciones CRUD.
 */
open class ProductRepository(
    private val remote: ProductRemoteDataSource
) {

    /**
     * Obtiene la lista completa de productos.
     *
     * @return Lista de productos en formato de dominio.
     */
    suspend fun getProducts(): List<Product> =
        remote.getAll().map { it.toDomain() }

    /**
     * Obtiene un producto concreto por su ID.
     *
     * @param id Identificador del producto.
     * @return Producto en formato de dominio.
     */
    suspend fun getProduct(id: Int): Product =
        remote.getById(id).toDomain()

    /**
     * Filtra productos según los criterios especificados.
     *
     * @param filter Objeto con los criterios de filtrado.
     * @return Lista de productos filtrados en formato de dominio.
     */
    suspend fun filterProducts(filter: ProductFilterDto): List<Product> =
        remote.filter(filter).map { it.toDomain() }

    /**
     * Obtiene la calificación media de un producto.
     *
     * @param id Identificador del producto.
     * @return Calificación media del producto o null si no tiene reseñas.
     */
    suspend fun getProductAverageRating(id: Int): Double? =
        remote.getAverageRating(id)

    /**
     * Obtiene las reseñas de un producto.
     *
     * @param id Identificador del producto.
     * @return Lista de reseñas en formato de dominio.
     */
    suspend fun getProductReviews(id: Int): List<Review> =
        remote.getReviews(id).data.map { it.toDomain() }
}
