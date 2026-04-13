package com.viu.actividad1_android.data.product.remote

import com.viu.actividad1_android.data.product.remote.api.ProductApi
import com.viu.actividad1_android.data.product.remote.dto.ProductDto
import com.viu.actividad1_android.data.product.remote.dto.ProductFilterDto

/**
 * Implementación fake de [ProductApi] para entornos sin backend disponible.
 *
 * Proporciona un conjunto fijo de productos simulados, útil para:
 * - desarrollo sin conexión
 * - pruebas locales
 * - fallback automático cuando el backend no responde
 *
 * Devuelve DTOs directamente, dejando el mapeo a dominio en la capa de repositorios.
 */
class FakeProductApi : ProductApi {

    /** Datos simulados de productos. */
    private val mockProducts = listOf(
        ProductDto(
            id = 1,
            name = "Catan",
            price = 35.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/catan.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 1
        ),
        ProductDto(
            id = 2,
            name = "Carcassonne",
            price = 25.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/carcassone.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 1
        ),
        ProductDto(
            id = 3,
            name = "Dixit",
            price = 30.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/dixit.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 2
        ),
        ProductDto(
            id = 4,
            name = "Virus!",
            price = 12.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/virus.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 3
        ),
        ProductDto(
            id = 5,
            name = "Terraforming Mars",
            price = 60.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/terraformingMars.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 4
        ),
        ProductDto(
            id = 6,
            name = "Exploding Kittens",
            price = 20.0,
            description = "",
            stock = 100,
            image = "https://ugigvb-actividad1-08masw.s3.us-east-1.amazonaws.com/explodingKittens.jpg",
            inactive = 0,
            categoryId = 1,
            supplierId = 2
        )
    )

    override suspend fun getAllProducts(): List<ProductDto> = mockProducts

    override suspend fun getProductById(id: Int): ProductDto =
        mockProducts.firstOrNull { it.id == id }
            ?: throw IllegalArgumentException("Producto con id $id no encontrado")

    override suspend fun filterProducts(body: ProductFilterDto): List<ProductDto> =
        mockProducts.filter { product ->
            val matchesName =
                body.name.isNullOrBlank() ||
                        product.name.contains(body.name, ignoreCase = true)

            val matchesCategory =
                body.category == null ||
                        product.categoryId == body.category

            val matchesSupplier =
                body.supplier == null ||
                        product.supplierId == body.supplier

            val matchesMin =
                body.min == null || product.price >= body.min

            val matchesMax =
                body.max == null || product.price <= body.max

            matchesName && matchesCategory && matchesSupplier && matchesMin && matchesMax
        }
}
