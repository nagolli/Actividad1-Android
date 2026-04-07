package com.viu.actividad1_android.activities.productGrid

import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

//Funciones de la vista product grid
sealed class ProductGridEvent {
    object LoadProducts : ProductGridEvent()
    data class OnFilterChanged(val filter: ProductFilterDto) : ProductGridEvent()
    object ApplyFilters : ProductGridEvent()
    data class OnProductClick(val id: Int) : ProductGridEvent()
}
