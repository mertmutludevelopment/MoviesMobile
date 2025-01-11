package com.example.moviesmobile.data.datasource

import com.example.moviesmobile.data.entity.SignInRequest
import com.example.moviesmobile.data.entity.SignInResponse
import com.example.moviesmobile.retrofit.AuthDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authDao: AuthDao
) {
    suspend fun signIn(email: String, password: String): SignInResponse = withContext(Dispatchers.IO) {
        return@withContext authDao.signIn(SignInRequest(email, password))
    }
} 