package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessTokenExpiration")
    val accessTokenExpiration: String,
    @SerializedName("refreshTokenExpiration")
    val refreshTokenExpiration: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("email")
    val email: String
)