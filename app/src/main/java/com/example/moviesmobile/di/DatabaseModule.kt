package com.example.moviesmobile.di

import android.content.Context
import androidx.room.Room
import com.example.moviesmobile.room.MovieDatabase
import com.example.moviesmobile.room.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module that provides database related dependencies
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    // Provides Room database instance
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    // Provides DAO for favorite movies operations
    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }
} 