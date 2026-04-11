package com.viu.actividad1_android.data.category.repository

import com.viu.actividad1_android.data.category.Category
import com.viu.actividad1_android.data.category.mapper.toDomain
import com.viu.actividad1_android.data.category.remote.CategoryRemoteDataSource

open class CategoryRepository(
    private val remote: CategoryRemoteDataSource
) {

    suspend fun getCategories(): List<Category> =
        remote.getAll().map { it.toDomain() }

    suspend fun getCategory(id: Int): Category =
        remote.getById(id).toDomain()

}
