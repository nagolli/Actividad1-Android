package com.viu.actividad1_android.activities.cart

import com.viu.actividad1_android.data.product.Product

data class CartItem(
    val quantity: Int,
    val item: Product
) {
    val name: String
        get() = item.name;
    val id: Int
        get() = item.id;
    val price: Double
        get() = item.price;
    val imageUrl: String?
        get() = item.imageUrl;
}