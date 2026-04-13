package com.viu.actividad1_android.activities.productDetail

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.Review

/**
 * Estado de la pantalla de detalles de producto.
 *
 * @param isLoading Indica si se está cargando la información.
 * @param product El producto a mostrar, o null si aún no se ha cargado.
 * @param reviews Lista de reseñas del producto.
 * @param averageRating Calificación media del producto o null si no tiene reseñas.
 * @param quantity Cantidad de producto seleccionada.
 * @param error Mensaje de error en caso de que ocurra alguno.
 */
data class ProductDetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val reviews: List<Review> = emptyList(),
    val averageRating: Double? = null,
    val quantity: Int = 1,
    val error: String? = null
)
