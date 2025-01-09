package com.example.moviesmobile.retrofit

import com.example.moviesmobile.constants.AppConstants

class ApiUtils {

    companion object{
        fun getFilmlerDao() : MoviesDao {
            return RetrofitClient.getClient(AppConstants.BASE_URL).create(MoviesDao::class.java)
        }
    }
}