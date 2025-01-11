package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.CartMovie

// Interface defining movie-related operations for the application
interface IMovieRepository {
    // Fetches all available movies
    suspend fun getAllMovies(): List<Movie>
    
    // Retrieves movie image by name
    suspend fun getMovieImage(imageName: String): String
    
    // Gets specific movie details by ID
    suspend fun getMovieById(movieId: Int): Movie?
    
    // Adds movie to user's cart
    suspend fun addToCart(movie: Movie, amount: Int): BaseResponse
    
    // Retrieves user's movie cart
    suspend fun getMovieCart(userName: String): List<CartMovie>
    
    // Removes movie from cart
    suspend fun deleteMovie(cartId: Int, userName: String): BaseResponse
} 