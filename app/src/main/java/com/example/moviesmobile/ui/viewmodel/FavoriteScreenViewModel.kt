package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviesmobile.data.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor() : ViewModel() {
    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

    fun addToFavorites(movie: Movie) {
        val currentList = _favoriteMovies.value.toMutableList()
        if (!currentList.contains(movie)) {
            currentList.add(movie)
            _favoriteMovies.value = currentList
        }
    }

    fun removeFromFavorites(movie: Movie) {
        val currentList = _favoriteMovies.value.toMutableList()
        currentList.remove(movie)
        _favoriteMovies.value = currentList
    }
} 