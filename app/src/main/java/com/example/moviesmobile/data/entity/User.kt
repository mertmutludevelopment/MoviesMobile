package com.example.moviesmobile.data.entity

data class User(
    val email: String,
    val fullName: String,
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiration: String,
    val refreshTokenExpiration: String
) 