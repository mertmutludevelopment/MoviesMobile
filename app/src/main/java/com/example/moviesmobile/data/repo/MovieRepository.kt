package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.datasource.MovieDataSource
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.BaseResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(var movieDataSource: MovieDataSource) {
    
    suspend fun getAllMovies(): List<Movie> {
        return movieDataSource.getAllMovies()
    }

    suspend fun getMovieImage(imageName: String): String {
        return movieDataSource.getMovieImage(imageName)
    }
    
    suspend fun getMovieById(movieId: Int): Movie? {
        return movieDataSource.getMovieById(movieId)
    }

    suspend fun addToCart(movie: Movie, amount: Int): BaseResponse {
        return movieDataSource.addToCart(movie, amount)
    }

    suspend fun getMovieCart(userName: String): List<Movie> {
        return movieDataSource.getMovieCart(userName)
    }
}