package com.example.moviesmobile.data.datasource

import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.retrofit.MoviesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieDataSource(var moviesDao: MoviesDao)
{

    suspend fun getAllMovies() : List<Movie> = withContext(Dispatchers.IO){
        return@withContext moviesDao.getAllMovies().movies
    }
}