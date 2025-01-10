package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing main screen movie listings
// Handles movie data loading and image URL management
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // State flows for movie list and their corresponding image URLs
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _movieImages = MutableStateFlow<Map<String, String>>(emptyMap())
    val movieImages: StateFlow<Map<String, String>> = _movieImages

    init {
        loadMovies()
    }

    // Fetch movie list from repository
    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val movieList = movieRepository.getAllMovies()
                _movies.value = movieList
                loadMovieImages(movieList)
            } catch (e: Exception) {
                Log.e("MainScreenViewModel", "Error loading movies: ${e.message}")
            }
        }
    }

    // Load image URLs for each movie
    private suspend fun loadMovieImages(movies: List<Movie>) {
        val imageMap = mutableMapOf<String, String>()
        movies.forEach { movie ->
            try {
                val imageUrl = movieRepository.getMovieImage(movie.image)
                imageMap[movie.image] = imageUrl
            } catch (e: Exception) {
                Log.e("MainScreenViewModel", "Error loading image for ${movie.name}: ${e.message}")
            }
        }
        _movieImages.value = imageMap
    }
}