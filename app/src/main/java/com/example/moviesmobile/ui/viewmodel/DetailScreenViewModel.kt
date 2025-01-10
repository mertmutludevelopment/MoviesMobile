package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.repo.MovieRepository
import com.example.moviesmobile.util.MovieDescriptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing movie details and cart operations
// Handles movie loading, description generation and cart additions
@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // State flows for movie details and description
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    private val _description = MutableStateFlow<String>("")
    val description: StateFlow<String> = _description

    // Load movie details and generate description
    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val movie = movieRepository.getMovieById(movieId)
                _movie.value = movie
                
                movie?.let {
                    _description.value = MovieDescriptions.getDescription(
                        movieName = it.name,
                        director = it.director,
                        year = it.year
                    )
                }
            } catch (e: Exception) {
                Log.e("DetailScreenViewModel", "Error loading movie: ${e.message}")
            }
        }
    }

    // Add or update movie in cart with specified amount
    fun addToCart(amount: Int) {
        viewModelScope.launch {
            try {
                movie.value?.let { currentMovie ->
                    val cartItems = movieRepository.getMovieCart(AppConstants.DEFAULT_USERNAME)
                    val existingItem = cartItems.find { it.name == currentMovie.name }
                    
                    if (existingItem != null) {
                        movieRepository.deleteMovie(existingItem.cartId, AppConstants.DEFAULT_USERNAME)
                        val totalAmount = existingItem.orderAmount + amount
                        val response = movieRepository.addToCart(currentMovie, totalAmount)
                        Log.d("CartOperation", "Ürün güncellendi: ${response.message}")
                    } else {
                        val response = movieRepository.addToCart(currentMovie, amount)
                        Log.d("CartOperation", "Yeni ürün eklendi: ${response.message}")
                    }
                }
            } catch (e: Exception) {
                Log.e("CartOperation", "Sepete eklerken hata: ${e.message}")
            }
        }
    }
} 