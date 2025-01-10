package com.example.moviesmobile.room

import androidx.room.*
import com.example.moviesmobile.data.entity.FavoriteMovie
import kotlinx.coroutines.flow.Flow

// Data Access Object for favorite movies database operations
@Dao
interface FavoriteMovieDao {
    // Gets all favorite movies with real-time updates
    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): Flow<List<FavoriteMovie>>

    // Adds movie to favorites or updates if exists
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: FavoriteMovie)

    // Removes a movie from favorites
    @Delete
    suspend fun deleteFavorite(movie: FavoriteMovie)

    // Checks if a movie is marked as favorite
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE movieId = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean
} 