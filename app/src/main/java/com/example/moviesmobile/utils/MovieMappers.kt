package com.example.moviesmobile.utils

import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.FavoriteMovie

//Helper function to convert FavoriteMovie entity to Movie model
object MovieMappers {
    // Movie -> FavoriteMovie conversion
    fun Movie.toFavoriteMovie() = FavoriteMovie(
        movieId = id,
        name = name,
        image = image,
        category = category,
        price = price,
        rating = rating,
        year = year,
        director = director,
        description = description
    )

    // FavoriteMovie -> Movie conversion
    fun FavoriteMovie.toMovie() = Movie(
        id = movieId,
        name = name,
        image = image,
        category = category,
        price = price,
        rating = rating,
        year = year,
        director = director,
        description = description
    )
} 