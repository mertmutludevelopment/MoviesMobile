package com.example.moviesmobile.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesmobile.data.entity.FavoriteMovie

// Room database class for storing favorite movies locally
@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    // Provides access to favorite movie operations
    abstract fun favoriteMovieDao(): FavoriteMovieDao
} 