package com.viu.actividad1_android.data.category.remote

import com.viu.actividad1_android.data.category.remote.api.CategoryApi

open class CategoryRemoteDataSource(
    private val api: CategoryApi
) {

    open suspend fun getAll() = api.getAllCategories()

    open suspend fun getById(id: Int) = api.getCategoryById(id)

}