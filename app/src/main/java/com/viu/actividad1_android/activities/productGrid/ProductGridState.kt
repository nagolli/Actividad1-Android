package com.viu.actividad1_android.activities.productGrid

import com.viu.actividad1_android.data.product.Product
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Estado de la pantalla Product Grid.
 *
 * Representa toda la información necesaria para renderizar la vista,
 * incluyendo la lista de productos, los filtros aplicados, los datos
 * auxiliares (categorías y proveedores) y estados de carga o error.
 *
 * Este estado es gestionado por el ViewModel siguiendo el patrón MVVM.
 *
 * @param products Lista de productos filtrados o cargados desde el repositorio.
 * @param isLoading Indica si se está realizando una operación de carga.
 * @param error Mensaje de error en caso de fallo en la carga de datos.
 * @param filter Filtros aplicados actualmente por el usuario.
 * @param categories Lista de categorías disponibles (id, nombre).
 * @param suppliers Lista de proveedores disponibles (id, nombre).
 * @param maxPrice Precio máximo detectado para configurar el slider de filtros.
 */
data class ProductGridState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val filter: ProductFilterDto = ProductFilterDto(
        name = null,
        category = null,
        supplier = null,
        min = null,
        max = null
    ),
    val categories: List<Pair<Int, String>> = emptyList(),
    val suppliers: List<Pair<Int, String>> = emptyList(),
    val maxPrice: Float = 0f
)
