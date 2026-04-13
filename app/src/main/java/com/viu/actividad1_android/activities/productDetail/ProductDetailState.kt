package com.viu.actividad1_android.activities.productDetail

import com.viu.actividad1_android.data.product.Product

/**
 * Estado de la pantalla de detalles de producto.
 *
 * @param isLoading Indica si se está cargando la información.
 * @param product El producto a mostrar, o null si aún no se ha cargado.
 * @param error Mensaje de error en caso de que ocurra alguno.
 */
data class ProductDetailState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)
