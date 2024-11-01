package com.gleb.lemana.task.domain.model

data class ProductDomainModel(
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    val image: String,
    val rating: String,
    val isLiked: Boolean = false,
    val inCartCount: Int = 0
)
