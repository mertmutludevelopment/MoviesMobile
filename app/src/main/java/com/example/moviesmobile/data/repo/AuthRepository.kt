package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.datasource.AuthDataSource
import com.example.moviesmobile.data.entity.SignInResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource
) : IAuthRepository {
    
    override suspend fun signIn(email: String, password: String): SignInResponse {
        return authDataSource.signIn(email, password)
    }
} 