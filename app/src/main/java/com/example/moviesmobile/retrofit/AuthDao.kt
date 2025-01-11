package com.example.moviesmobile.retrofit

import com.example.moviesmobile.data.entity.SignInRequest
import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.data.entity.SignUpRequest
import com.example.moviesmobile.data.entity.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

// Data Access Object interface for authentication API endpoints
interface AuthDao {
    // Handles user sign-in request
    @POST("api/SignIn")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse

    // Handles new user registration
    @POST("api/Signup")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse
}