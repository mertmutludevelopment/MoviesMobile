package com.example.moviesmobile.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton class that provides Retrofit instance for API calls
class RetrofitClient {

    companion object{
        // Creates and configures Retrofit instance with base URL and JSON converter
        fun getClient(baseUrl:String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}