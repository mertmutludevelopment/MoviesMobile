package com.example.moviesmobile.retrofit

import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.BaseResponse
import com.example.moviesmobile.data.entity.CartResponse
import com.example.moviesmobile.data.entity.MovieResponse
import retrofit2.http.*

interface MoviesDao {
    @GET(AppConstants.GET_ALL_MOVIES)
    suspend fun getAllMovies(): MovieResponse

    @GET("movies/images/{imageName}")
    suspend fun getMovieImage(
        @Path("imageName") imageName: String
    ): String

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

    @FormUrlEncoded
    @POST(AppConstants.GET_MOVIE_CART)
    suspend fun getMovieCart(
        @Field("userName") userName: String
    ): CartResponse

    @FormUrlEncoded
    @POST(AppConstants.DELETE_MOVIE)
    suspend fun deleteMovie(
        @Field("cartId") cartId: Int,
        @Field("userName") userName: String
    ): BaseResponse
}