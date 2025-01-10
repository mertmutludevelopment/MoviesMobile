package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor() : ViewModel() {
    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

    private val _favoriteStates = MutableStateFlow<Map<Int, Boolean>>(mapOf())
    val favoriteStates: StateFlow<Map<Int, Boolean>> = _favoriteStates

    fun toggleFavorite(movie: Movie) {
        val currentList = _favoriteMovies.value.toMutableList()
        val currentStates = _favoriteStates.value.toMutableMap()
        
        if (currentList.contains(movie)) {
            currentList.remove(movie)
            currentStates[movie.id] = false
        } else {
            currentList.add(movie)
            currentStates[movie.id] = true
        }
        _favoriteMovies.value = currentList
        _favoriteStates.value = currentStates
    }

    fun isFavorite(movie: Movie): Boolean {
        return _favoriteStates.value[movie.id] ?: _favoriteMovies.value.contains(movie)
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            val currentList = _favoriteMovies.value.toMutableList()
            val currentStates = _favoriteStates.value.toMutableMap()
            
            currentList.remove(movie)
            currentStates[movie.id] = false
            
            _favoriteMovies.value = currentList
            _favoriteStates.value = currentStates

            if (currentList.isEmpty()) {
                delay(100)
                _favoriteMovies.value = emptyList()
            }
        }
    }

    fun syncFavoriteStates() {
        val states = _favoriteMovies.value.associate { it.id to true }
        _favoriteStates.value = states
    }
} 