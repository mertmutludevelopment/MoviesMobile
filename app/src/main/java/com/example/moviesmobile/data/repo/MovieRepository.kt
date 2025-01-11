package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.datasource.MovieDataSource
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.CartMovie
import javax.inject.Inject

// Repository class that handles all movie-related operations through MovieDataSource
class MovieRepository @Inject constructor(var movieDataSource: MovieDataSource) : IMovieRepository {
    
    // Gets all available movies from data source
    override suspend fun getAllMovies(): List<Movie> {
        return movieDataSource.getAllMovies()
    }

    // Retrieves movie image URL
    override suspend fun getMovieImage(imageName: String): String {
        return movieDataSource.getMovieImage(imageName)
    }
    
    // Gets specific movie by ID
    override suspend fun getMovieById(movieId: Int): Movie? {
        return movieDataSource.getMovieById(movieId)
    }

    // Adds movie to shopping cart with specified amount
    override suspend fun addToCart(movie: Movie, amount: Int): BaseResponse {
        return movieDataSource.addToCart(movie, amount)
    }

    // Gets all movies in user's cart
    override suspend fun getMovieCart(userName: String): List<CartMovie> {
        return movieDataSource.getMovieCart(userName)
    }

    // Removes movie from cart
    override suspend fun deleteMovie(cartId: Int, userName: String): BaseResponse {
        return movieDataSource.deleteMovie(cartId, userName)
    }
}