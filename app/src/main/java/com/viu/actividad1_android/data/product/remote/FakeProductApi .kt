package com.viu.actividad1_android.data.product.remote

import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

class FakeProductApi  : ProductApi {

    private val mockProducts = listOf(
        ProductDto(1, "Catan", 35.0, "", 100, null,false, 1, 1),
        ProductDto(2, "Carcassonne", 25.0, "", 100, null,false, 1, 1),
        ProductDto(3, "Dixit", 30.0, "", 100, null,false, 1, 1),
        ProductDto(4, "Virus!", 12.0, "", 100, null,false, 1, 1),
        ProductDto(5, "Terraforming Mars", 60.0, "", 100, null,false, 1, 1),
        ProductDto(6, "Exploding Kittens", 20.0, "", 100, null,false, 1, 1)
    )

    override suspend fun getAllProducts(): List<ProductDto> = mockProducts

    override suspend fun getProductById(id: Int): ProductDto =
        mockProducts.first { it.id == id }

    override suspend fun filterProducts(body: ProductFilterDto): List<ProductDto> =
        mockProducts.filter {
            body.name == null || it.name.contains(body.name, ignoreCase = true)
        }
}
