package com.example.moviesmobile.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey
    val movieId: Int,
    val name: String,
    val image: String,
    val category: String,
    val price: Int,
    val rating: Double?,
    val year: Int,
    val director: String?,
    val description: String
) 