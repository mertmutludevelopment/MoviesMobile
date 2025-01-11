package com.example.moviesmobile.retrofit

import com.example.moviesmobile.data.entity.SignInRequest
import com.example.moviesmobile.data.entity.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthDao {
    @POST("api/SignIn")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse
}