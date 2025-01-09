package com.example.moviesmobile.retrofit

import com.example.moviesmobile.data.entity.MovieResponse
import com.example.moviesmobile.data.entity.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field

interface MoviesDao {
    @GET("movies/getAllMovies.php")
    suspend fun getAllMovies() : MovieResponse

    @GET
    suspend fun getMovieImage(@Url imageUrl: String): String

    @FormUrlEncoded
    @POST("movies/insertMovie.php")
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
}