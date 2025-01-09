package com.example.moviesmobile.data.entity

data class CartResponse(
    val movie_cart: List<CartMovie> = emptyList()
) 