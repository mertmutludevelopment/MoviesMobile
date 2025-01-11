package com.example.moviesmobile.di

import com.example.moviesmobile.data.datasource.MovieDataSource
import com.example.moviesmobile.data.repo.MovieRepository
import com.example.moviesmobile.retrofit.ApiUtils
import com.example.moviesmobile.retrofit.MoviesDao
import com.example.moviesmobile.data.manager.SessionManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

// Dependency injection module that provides singleton instances for the application
// Handles network and repository related dependencies
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    // Provides MoviesDao instance for API operations using Retrofit
    @Provides
    @Singleton
    fun provideMoviesDao(): MoviesDao {
        return ApiUtils.getFilmlerDao()
    }

    // Provides MovieDataSource instance for handling data operations
    @Provides
    @Singleton
    fun provideMovieDataSource(moviesDao: MoviesDao): MovieDataSource {
        return MovieDataSource(moviesDao)
    }

    // Provides MovieRepository instance as single source of truth
    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepository(movieDataSource)
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
} 