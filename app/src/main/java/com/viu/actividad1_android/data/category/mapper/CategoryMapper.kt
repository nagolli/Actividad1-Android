package com.viu.actividad1_android.data.category.mapper

import com.viu.actividad1_android.data.category.Category
import com.viu.actividad1_android.data.category.remote.dto.CategoryDto

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name
)
