package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _movieImages = MutableStateFlow<Map<String, String>>(emptyMap())
    val movieImages: StateFlow<Map<String, String>> = _movieImages

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val movieList = movieRepository.getAllMovies()
                _movies.value = movieList
                loadMovieImages(movieList)
            } catch (e: Exception) {
                // Hata durumunda gerekli işlemler yapılabilir
            }
        }
    }

    private suspend fun loadMovieImages(movies: List<Movie>) {
        val imageMap = mutableMapOf<String, String>()
        movies.forEach { movie ->
            try {
                val imageUrl = movieRepository.getMovieImage(movie.image)
                imageMap[movie.image] = imageUrl
            } catch (e: Exception) {
                // Hata durumunda işlemler
            }
        }
        _movieImages.value = imageMap
    }
}