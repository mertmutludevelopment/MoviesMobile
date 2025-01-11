package com.example.moviesmobile.data.datasource

import com.example.moviesmobile.data.entity.SignInRequest
import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.data.entity.SignUpRequest
import com.example.moviesmobile.data.entity.SignUpResponse
import com.example.moviesmobile.retrofit.AuthDao
import com.example.moviesmobile.data.manager.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import retrofit2.Response

/**
 * Data source class that handles authentication operations
 * Uses AuthDao to make API calls for sign-in and sign-up functionality
 */
class AuthDataSource @Inject constructor(
    private val authDao: AuthDao,
    private val sessionManager: SessionManager
) {
    suspend fun signIn(email: String, password: String): SignInResponse = withContext(Dispatchers.IO) {
        return@withContext authDao.signIn(SignInRequest(email, password))
    }

    suspend fun signUp(fullName: String, email: String, password: String): SignUpResponse = withContext(Dispatchers.IO) {
        val request = SignUpRequest(
            fullName = fullName,
            email = email,
            password = password
        )
        return@withContext authDao.signUp(request)
    }
} 