package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.CartMovie
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

    private val _cartItems = MutableStateFlow<List<CartMovie>>(emptyList())
    val cartItems: StateFlow<List<CartMovie>> = _cartItems

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                val items = movieRepository.getMovieCart("mert_mutlu")
                _cartItems.value = items
            } catch (e: Exception) {
                Log.e("CartOperation", "Sepet yüklenirken hata: ${e.message}")
            }
        }
    }

    fun deleteMovie(cartId: Int) {
        viewModelScope.launch {
            try {
                val response = movieRepository.deleteMovie(cartId, "mert_mutlu")
                if (response.success == 1) {
                    Log.d("CartOperation", "Film sepetten silindi: cartId=$cartId")
                    loadCartItems() // Sepeti yeniden yükle
                } else {
                    Log.e("CartOperation", "Film silinemedi: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("CartOperation", "Film silinirken hata: ${e.message}")
            }
        }
    }
}