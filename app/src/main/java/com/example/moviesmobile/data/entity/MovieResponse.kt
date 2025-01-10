package com.example.moviesmobile.data.entity

import com.google.gson.annotations.SerializedName

// Response wrapper class for movie list from API
data class MovieResponse (
    @SerializedName("movies")
    val movies: List<Movie>,
    @SerializedName("success")
    val success: Int = 1
){
}