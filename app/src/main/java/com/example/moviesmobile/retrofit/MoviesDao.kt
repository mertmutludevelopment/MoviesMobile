package com.example.moviesmobile.retrofit

import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.CartResponse
import com.example.moviesmobile.data.entity.MovieResponse
import retrofit2.http.*

// Data Access Object interface for handling all movie-related API requests
interface MoviesDao {
    // Fetches complete movie list from server
    @GET(AppConstants.GET_ALL_MOVIES)
    suspend fun getAllMovies(): MovieResponse

    // Gets movie poster image by name
    @GET("movies/images/{imageName}")
    suspend fun getMovieImage(
        @Path("imageName") imageName: String
    ): String

    // Adds new movie to user's cart or updates existing one
    @FormUrlEncoded
    @POST(AppConstants.INSERT_MOVIE)
    suspend fun addToCart(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("price") price: Int,
        @Field("category") category: String,
        @Field("rating") rating: Double,
        @Field("year") year: Int,
        @Field("director") director: String,
        @Field("description") description: String,
        @Field("orderAmount") orderAmount: Int,
        @Field("userName") userName: String
    ): BaseResponse

    // Gets all movies in user's cart
    @FormUrlEncoded
    @POST(AppConstants.GET_MOVIE_CART)
    suspend fun getMovieCart(
        @Field("userName") userName: String
    ): CartResponse

    // Removes specific movie from user's cart
    @FormUrlEncoded
    @POST(AppConstants.DELETE_MOVIE)
    suspend fun deleteMovie(
        @Field("cartId") cartId: Int,
        @Field("userName") userName: String
    ): BaseResponse
}