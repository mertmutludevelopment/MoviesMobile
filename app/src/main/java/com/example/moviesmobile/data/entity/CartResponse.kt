package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String
) 