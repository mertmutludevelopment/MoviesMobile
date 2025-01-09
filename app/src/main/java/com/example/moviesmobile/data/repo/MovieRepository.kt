package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.datasource.MovieDataSource
import com.example.moviesmobile.data.entity.Movie
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
}