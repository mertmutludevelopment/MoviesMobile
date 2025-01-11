package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.data.entity.SignUpResponse

// Interface defining authentication operations for the application
interface IAuthRepository {
    // Authenticates user with email and password
    suspend fun signIn(email: String, password: String): SignInResponse
    // Registers new user with provided details
    suspend fun signUp(fullName: String, email: String, password: String): SignUpResponse
} 