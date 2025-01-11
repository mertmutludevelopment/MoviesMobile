package com.example.moviesmobile.di

import android.util.Log
import com.example.moviesmobile.data.datasource.AuthDataSource
import com.example.moviesmobile.data.repo.AuthRepository
import com.example.moviesmobile.data.repo.IAuthRepository
import com.example.moviesmobile.retrofit.AuthDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    
    /**
     * Provides OkHttpClient with logging interceptor and timeout configurations
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("API Request", "URL: ${request.url}")
                Log.d("API Request", "Method: ${request.method}")
                Log.d("API Request", "Headers: ${request.headers}")
                
                val response = chain.proceed(request)
                Log.d("API Response", "Code: ${response.code}")
                Log.d("API Response", "Message: ${response.message}")
                
                response
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides AuthDao instance using Retrofit with base URL for emulator
     */
    @Provides
    @Singleton
    fun provideAuthDao(okHttpClient: OkHttpClient): AuthDao {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5280/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthDao::class.java)
    }

    /**
     * Provides AuthDataSource instance
     */
    @Provides
    @Singleton
    fun provideAuthDataSource(authDao: AuthDao): AuthDataSource {
        return AuthDataSource(authDao)
    }

    /**
     * Provides IAuthRepository implementation
     */
    @Provides
    @Singleton
    fun provideAuthRepository(authDataSource: AuthDataSource): IAuthRepository {
        return AuthRepository(authDataSource)
    }
}