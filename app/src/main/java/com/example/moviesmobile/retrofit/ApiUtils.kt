package com.example.moviesmobile.retrofit

class ApiUtils {

    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFilmlerDao() : MoviesDao {
            return RetrofitClient.getClient(BASE_URL).create(MoviesDao::class.java)
        }
    }
}