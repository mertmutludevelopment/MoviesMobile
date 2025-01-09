package com.example.moviesmobile.data.datasource

import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.retrofit.MoviesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(var moviesDao: MoviesDao) {

    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        return@withContext moviesDao.getAllMovies().movies
    }

    suspend fun getMovieImage(imageName: String): String = withContext(Dispatchers.IO) {
        val imageUrl = "movies/images/$imageName"
        return@withContext moviesDao.getMovieImage(imageUrl)
    }
    
    suspend fun getMovieById(movieId: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext moviesDao.getAllMovies().movies.find { it.id == movieId }
    }

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
            userName = "mert_mutlu"
        )
    }

    suspend fun getMovieCart(userName: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            moviesDao.getMovieCart(userName).movie_cart
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun deleteMovie(cartId: Int, userName: String) = withContext(Dispatchers.IO) {
        return@withContext moviesDao.deleteMovie(cartId, userName)
    }
}