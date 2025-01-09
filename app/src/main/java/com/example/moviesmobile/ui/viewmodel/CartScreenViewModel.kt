package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<Movie>>(emptyList())
    val cartItems: StateFlow<List<Movie>> = _cartItems

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                val items = movieRepository.getMovieCart("mert_mutlu")
                _cartItems.value = items
            } catch (e: Exception) {
                // Hata durumunda işlemler
            }
        }
    }
}