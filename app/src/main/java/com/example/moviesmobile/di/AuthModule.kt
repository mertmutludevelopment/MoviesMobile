package com.example.moviesmobile.di

import android.util.Log
import com.example.moviesmobile.data.datasource.AuthDataSource
import com.example.moviesmobile.data.repo.AuthRepository
import com.example.moviesmobile.data.repo.IAuthRepository
import com.example.moviesmobile.retrofit.AuthDao
import com.example.moviesmobile.data.manager.SessionManager
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.retrofit.ApiUtils
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
     * Provides AuthDao instance using RetrofitClient
     */
    @Provides
    @Singleton
    fun provideAuthDao(): AuthDao {
        return ApiUtils.getAuthDao()
    }

    /**
     * Provides AuthDataSource instance
     */
    @Provides
    @Singleton
    fun provideAuthDataSource(
        authDao: AuthDao,
        sessionManager: SessionManager
    ): AuthDataSource {
        return AuthDataSource(authDao, sessionManager)
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