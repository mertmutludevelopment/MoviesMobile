package com.example.moviesmobile.data.datasource

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
}