package com.example.moviesmobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesmobile.data.entity.FavoriteMovie
import com.example.moviesmobile.data.local.dao.FavoriteMovieDao

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
} 