package com.example.moviesmobile.data.entity

// Response wrapper class for shopping cart items from API
data class CartResponse(
    val movie_cart: List<CartMovie> = emptyList()
) 