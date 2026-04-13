package com.viu.actividad1_android.data.product.remote.api

import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto
import com.viu.actividad1_android.data.product.remote.dto.ReviewResponseDto
import retrofit2.http.*

/**
 * Interfaz Retrofit para acceder a los endpoints remotos relacionados
 * con productos.
 *
 * Define las rutas expuestas por el backend y devuelve DTOs que serán
 * posteriormente mapeados a modelos de dominio en la capa de repositorios.
 */
interface ProductApi {

    /**
     * Obtiene la lista completa de productos desde el backend.
     *
     * @return Lista de [ProductDto] recibida de la API.
     */
    @GET("product")
    suspend fun getAllProducts(): List<ProductDto>

    /**
     * Obtiene un producto concreto por su ID.
     *
     * @param id Identificador del producto.
     * @return Un [ProductDto] correspondiente al producto solicitado.
     */
    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    /**
     * Filtra productos según los criterios especificados.
     *
     * @param body Objeto con los criterios de filtrado.
     * @return Lista de productos filtrados en formato DTO.
     */
    @POST("product/filter")
    suspend fun filterProducts(@Body body: ProductFilterDto): List<ProductDto>

    /**
     * Obtiene la calificación media de un producto.
     *
     * @param id Identificador del producto.
     * @return Calificación media del producto.
     */
    @GET("review/{id}/average-rating")
    suspend fun getProductAverageRating(@Path("id") id: Int): Double?

    /**
     * Obtiene las reseñas de un producto.
     *
     * @param id Identificador del producto.
     * @return Lista de reseñas envueltas en un objeto ReviewResponseDto.
     */
    @GET("review/product/{id}")
    suspend fun getProductReviews(@Path("id") id: Int): ReviewResponseDto
}
