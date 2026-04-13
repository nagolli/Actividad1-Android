package com.viu.actividad1_android.data.category.remote

import com.viu.actividad1_android.data.category.remote.api.CategoryApi
import com.viu.actividad1_android.data.category.remote.dto.CategoryDto

/**
 * Implementación fake de [CategoryApi] para entornos sin backend disponible.
 *
 * Proporciona un conjunto fijo de categorías simuladas, útil para desarrollo
 * offline, pruebas locales o fallback automático cuando el backend no responde.
 */
class FakeCategoryApi : CategoryApi {

    /** Lista fija de categorías simuladas. */
    private val mockCategories = listOf(
        CategoryDto(1, "Juego de mesa"),
        CategoryDto(2, "Manual de rol")
    )

    override suspend fun getAllCategories(): List<CategoryDto> =
        mockCategories

    override suspend fun getCategoryById(id: Int): CategoryDto =
        mockCategories.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Categoría con id $id no encontrada")
}
