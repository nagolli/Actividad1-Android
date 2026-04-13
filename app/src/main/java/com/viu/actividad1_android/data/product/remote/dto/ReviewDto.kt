package com.viu.actividad1_android.data.product.remote.dto

data class ReviewResponseDto(
    val data: List<ReviewDto>
)

data class ReviewDto(
    val productId: Int,
    val email: String?,
    val rating: Int,
    val review: String,
    val user: UserReviewDto
)

data class UserReviewDto(
    val email: String,
    val name: String
)
