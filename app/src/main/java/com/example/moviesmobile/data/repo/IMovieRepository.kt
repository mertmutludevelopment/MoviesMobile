package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.CartMovie

interface IMovieRepository {
    suspend fun getAllMovies(): List<Movie>
    
    suspend fun getMovieImage(imageName: String): String
    
    suspend fun getMovieById(movieId: Int): Movie?
    
    suspend fun addToCart(movie: Movie, amount: Int): BaseResponse
    
    suspend fun getMovieCart(userName: String): List<CartMovie>
    
    suspend fun deleteMovie(cartId: Int, userName: String): BaseResponse
} 