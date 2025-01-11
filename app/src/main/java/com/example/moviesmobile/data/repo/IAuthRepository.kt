package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.entity.SignInResponse

interface IAuthRepository {
    suspend fun signIn(email: String, password: String): SignInResponse
} 