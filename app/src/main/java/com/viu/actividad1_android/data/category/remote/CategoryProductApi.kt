package com.viu.actividad1_android.data.category.remote

import com.viu.actividad1_android.data.category.remote.api.CategoryApi
import com.viu.actividad1_android.data.category.remote.dto.CategoryDto

class FakeCategoryApi  : CategoryApi {

    private val mockCategories = listOf(
        CategoryDto(1, "Juego de mesa"),
        CategoryDto(2, "Manual de rol")
    )

    override suspend fun getAllCategories(): List<CategoryDto> = mockCategories

    override suspend fun getCategoryById(id: Int): CategoryDto =
        mockCategories.first { it.id == id }

}
