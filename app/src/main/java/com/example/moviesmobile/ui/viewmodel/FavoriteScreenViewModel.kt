package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.FavoriteMovie
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.room.FavoriteMovieDao
import com.example.moviesmobile.utils.MovieMappers.toFavoriteMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing favorite movies using Room database
// Handles favorite movie operations and state management
@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : ViewModel() {

    // StateFlow of favorite movies with WhileSubscribed sharing policy
    val favoriteMovies: StateFlow<List<FavoriteMovie>> = favoriteMovieDao.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Toggle favorite status of a movie (add/remove)
    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            try {
                val isFavorite = favoriteMovieDao.isFavorite(movie.id)
                if (isFavorite) {
                    val favoriteMovie = movie.toFavoriteMovie()
                    favoriteMovieDao.deleteFavorite(favoriteMovie)
                } else {
                    favoriteMovieDao.insertFavorite(movie.toFavoriteMovie())
                }
            } catch (e: Exception) {
                Log.e("FavoriteOperation", "Error toggling favorite: ${e.message}")
            }
        }
    }

    // Check if a movie is marked as favorite
    suspend fun isFavorite(movie: Movie): Boolean {
        return favoriteMovieDao.isFavorite(movie.id)
    }
} 