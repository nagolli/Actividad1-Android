package com.viu.actividad1_android.data.product.remote.api

import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PATCH
import retrofit2.http.Body
import retrofit2.http.Path


interface ProductApi {

    @GET("product")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @POST("product/filter")
    suspend fun filterProducts(@Body body: ProductFilterDto): List<ProductDto>

}
