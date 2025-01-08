package com.example.moviesmobile.di

import com.example.moviesmobile.data.datasource.MovieDataSource
import com.example.moviesmobile.data.repo.MovieRepository
import com.example.moviesmobile.retrofit.ApiUtils
import com.example.moviesmobile.retrofit.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideMoviesDao(): MoviesDao {
        return ApiUtils.getFilmlerDao()
    }

    @Provides
    @Singleton
    fun provideMovieDataSource(moviesDao: MoviesDao): MovieDataSource {
        return MovieDataSource(moviesDao)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepository(movieDataSource)
    }
} 