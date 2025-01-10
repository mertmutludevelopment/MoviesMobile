package com.example.moviesmobile.retrofit

import com.example.moviesmobile.constants.AppConstants

// Utility class that provides MoviesDao instance
class ApiUtils {

    companion object{
        // Creates MoviesDao instance using RetrofitClient
        fun getFilmlerDao() : MoviesDao {
            return RetrofitClient.getClient(AppConstants.BASE_URL).create(MoviesDao::class.java)
        }
    }
}