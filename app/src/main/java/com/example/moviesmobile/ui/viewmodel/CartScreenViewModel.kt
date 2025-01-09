package com.example.moviesmobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.data.entity.CartMovie
import com.example.moviesmobile.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showSuccess = MutableStateFlow(false)
    val showSuccess: StateFlow<Boolean> = _showSuccess

    init {
        loadCartItems()
    }

    fun loadCartItems() {
        viewModelScope.launch {
            try {
                val items = movieRepository.getMovieCart(AppConstants.DEFAULT_USERNAME)
                _cartItems.value = items
            } catch (e: Exception) {
                Log.e("CartOperation", "Sepet yüklenirken hata: ${e.message}")
            }
        }
    }

    fun deleteMovie(cartId: Int) {
        viewModelScope.launch {
            try {
                val response = movieRepository.deleteMovie(cartId, AppConstants.DEFAULT_USERNAME)
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

    fun clearAllCartItems() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val currentItems = _cartItems.value
                
                currentItems.forEach { cartMovie ->
                    movieRepository.deleteMovie(cartMovie.cartId, AppConstants.DEFAULT_USERNAME)
                }
                
                loadCartItems()
                _isLoading.value = false  // Önce loading'i kapat
                _showSuccess.value = true  // Sonra success'i göster
                
                // 2 saniye sonra success mesajını kaldır
                delay(2000)
                _showSuccess.value = false
                
            } catch (e: Exception) {
                Log.e("CartOperation", "Sepet temizlenirken hata: ${e.message}")
                _isLoading.value = false
            }
        }
    }
}