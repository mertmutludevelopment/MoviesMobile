package com.example.moviesmobile.constants

// Contains all constant values used throughout the application
object AppConstants {
    // Base URLs for API endpoints
    const val BASE_URL = "http://kasimadalan.pe.hu/"
    const val BASE_AUTH_URL = "http://10.0.2.2:5280/"  // Auth API base URL
    const val IMAGE_URL = "$BASE_URL/movies/images/"
    
    // Default user information
    const val DEFAULT_USERNAME = "mert_mutlu"
    
    // API endpoint paths
    const val GET_ALL_MOVIES = "movies/getAllMovies.php"
    const val GET_MOVIE_CART = "movies/getMovieCart.php"
    const val INSERT_MOVIE = "movies/insertMovie.php"
    const val DELETE_MOVIE = "movies/deleteMovie.php"
    
    // Discount settings
    const val DISCOUNT_CODE = "mert10"
    const val DISCOUNT_PERCENTAGE = 0.10 // %10 discount
} 