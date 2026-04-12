package com.viu.actividad1_android.data.category.repository

import com.viu.actividad1_android.data.getEndpoint
import com.viu.actividad1_android.data.isBackendAvailable
import com.viu.actividad1_android.data.category.remote.FakeCategoryApi
import com.viu.actividad1_android.data.category.remote.CategoryRemoteDataSource
import com.viu.actividad1_android.data.category.remote.api.CategoryApi
import com.viu.actividad1_android.data.category.remote.dto.CategoryDto


fun categoryFakeOrHttpDataSource(): CategoryRemoteDataSource {
    return if (isBackendAvailable()) {
        val api = getEndpoint<CategoryApi>()
        CategoryRemoteDataSource(api)
    } else {
        FakeCategoryRemoteDataSource()
    }
}


class FakeCategoryRepository : CategoryRepository(
    remote = FakeCategoryRemoteDataSource()
)

class FakeCategoryRemoteDataSource :
    CategoryRemoteDataSource(api = FakeCategoryApi()) {

    override suspend fun getAll() = super.getAll()

    override suspend fun getById(id: Int) = super.getById(id)

}
