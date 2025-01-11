package com.example.moviesmobile.data.repo

import com.example.moviesmobile.data.datasource.AuthDataSource
import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.data.entity.SignUpResponse
import javax.inject.Inject

// Implementation of IAuthRepository that handles authentication operations through AuthDataSource
class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource
) : IAuthRepository {
    // Delegates sign-in operation to AuthDataSource
    override suspend fun signIn(email: String, password: String): SignInResponse {
        return authDataSource.signIn(email, password)
    }

    // Delegates sign-up operation to AuthDataSource
    override suspend fun signUp(fullName: String, email: String, password: String): SignUpResponse {
        return authDataSource.signUp(fullName, email, password)
    }
} 