package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

// Represents a movie entity with all its details from the API
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("category")
    val category: String = "",
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("year")
    val year: Int,
    @SerializedName("director")
    val director: String?,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("orderAmount")
    val orderAmount: Int = 0,
    @SerializedName("userName")
    val userName: String = ""
)