package com.viu.actividad1_android.activities.productGrid

import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Representa los eventos que pueden ocurrir en la pantalla de Product Grid.
 *
 * Estos eventos son procesados por el ViewModel para cargar datos,
 * actualizar filtros o gestionar la navegación al detalle de un producto.
 */
sealed class ProductGridEvent {

    /** Solicita la carga inicial de productos. */
    object LoadProducts : ProductGridEvent()

    /** Solicita la carga de proveedores disponibles. */
    object LoadSuppliers : ProductGridEvent()

    /** Solicita la carga de categorías disponibles. */
    object LoadCategories : ProductGridEvent()

    /**
     * Evento que se dispara cuando el usuario modifica algún filtro.
     *
     * @param filter Nuevo estado del filtro.
     */
    data class OnFilterChanged(val filter: ProductFilterDto) : ProductGridEvent()

    /** Aplica los filtros seleccionados por el usuario. */
    object ApplyFilters : ProductGridEvent()

    /**
     * Evento que se dispara cuando el usuario selecciona un producto.
     *
     * @param id Identificador del producto seleccionado.
     */
    data class OnProductClick(val id: Int) : ProductGridEvent()
}
