package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

// Generic response model for API operations with success status and message
data class BaseResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String
) 