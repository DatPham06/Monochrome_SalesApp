package com.monochrome.app.model

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val category: String,
    val image: String,
    val shortDescription: String
)

data class CartItem(
    val product: Product,
    val quantity: Int
)
