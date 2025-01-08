package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("year")
    val year: Int = 0,
    @SerializedName("director")
    val director: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("language")
    val language: String? = null
) {
}