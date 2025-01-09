package com.example.moviesmobile.retrofit

import com.example.moviesmobile.data.entity.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface MoviesDao {
    @GET("movies/getAllMovies.php")
    suspend fun getAllMovies() : MovieResponse

    @GET
    suspend fun getMovieImage(@Url imageUrl: String): String
}