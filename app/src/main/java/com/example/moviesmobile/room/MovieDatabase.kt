package com.example.moviesmobile.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesmobile.data.entity.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
} 