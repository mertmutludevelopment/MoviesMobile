package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

//Data class representing the sign-in request payload sent to the API
data class SignInRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)