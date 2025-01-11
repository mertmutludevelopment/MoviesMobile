package com.example.moviesmobile.data.datasource

import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.retrofit.MoviesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Handles all data operations using MoviesDao
class MovieDataSource @Inject constructor(var moviesDao: MoviesDao) {

    // Fetches all movies from the remote data source
    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        return@withContext moviesDao.getAllMovies().movies
    }

    // Gets the full image URL for a movie
    suspend fun getMovieImage(imageName: String): String = withContext(Dispatchers.IO) {
        val imageUrl = "${AppConstants.IMAGE_URL}$imageName"
        return@withContext moviesDao.getMovieImage(imageUrl)
    }
    
    // Retrieves a specific movie by its ID
    suspend fun getMovieById(movieId: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext moviesDao.getAllMovies().movies.find { it.id == movieId }
    }

    // Adds a movie to the user's cart with specified amount
    suspend fun addToCart(movie: Movie, amount: Int) = withContext(Dispatchers.IO) {
        val movieDetails = getMovieById(movie.id) ?: return@withContext BaseResponse(0, "Movie not found")
        
        return@withContext moviesDao.addToCart(
            name = movieDetails.name,
            image = movieDetails.image,
            price = movieDetails.price,
            category = movieDetails.category,
            rating = movieDetails.rating ?: 0.0,
            year = movieDetails.year,
            director = movieDetails.director ?: "",
            description = movieDetails.description,
            orderAmount = amount,
            userName = AppConstants.DEFAULT_USERNAME
        )
    }

    // Gets all movies in user's cart
    suspend fun getMovieCart(userName: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            moviesDao.getMovieCart(userName).movie_cart
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Removes a movie from the cart
    suspend fun deleteMovie(cartId: Int, userName: String) = withContext(Dispatchers.IO) {
        return@withContext moviesDao.deleteMovie(cartId, userName)
    }
}