package com.example.moviesmobile.retrofit

import com.example.moviesmobile.data.entity.MovieResponse
import retrofit2.http.GET

interface MoviesDao {


    //Dao : Database Access Object
    //http://kasimadalan.pe.hu/kisiler/tum_kisiler.php
    //http://kasimadalan.pe.hu/ -> base url
    //kisiler/tum_kisiler.php -> api url


    @GET("movies/getAllMovies.php")
    suspend fun getAllMovies() : MovieResponse
}