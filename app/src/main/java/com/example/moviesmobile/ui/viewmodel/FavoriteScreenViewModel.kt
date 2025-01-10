package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.FavoriteMovie
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.local.dao.FavoriteMovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : ViewModel() {

    val favoriteMovies: StateFlow<List<FavoriteMovie>> = favoriteMovieDao.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

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
                // Hata durumunda gerekli işlemler yapılabilir
            }
        }
    }

    suspend fun isFavorite(movie: Movie): Boolean {
        return favoriteMovieDao.isFavorite(movie.id)
    }

    private fun Movie.toFavoriteMovie() = FavoriteMovie(
        movieId = id,
        name = name,
        image = image,
        category = category,
        price = price,
        rating = rating,
        year = year,
        director = director,
        description = description
    )
} 