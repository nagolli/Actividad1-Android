package com.viu.actividad1_android.data.category.remote.api

import com.viu.actividad1_android.data.category.remote.dto.CategoryDto

import retrofit2.http.GET
import retrofit2.http.Path


interface CategoryApi {

    @GET("category")
    suspend fun getAllCategories(): List<CategoryDto>

    @GET("category/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): CategoryDto
}
