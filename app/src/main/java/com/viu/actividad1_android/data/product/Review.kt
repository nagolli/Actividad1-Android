package com.viu.actividad1_android.data.product

data class Review(
    val productId: Int,
    val rating: Int,
    val review: String,
    val userName: String,
    val userEmail: String
)
