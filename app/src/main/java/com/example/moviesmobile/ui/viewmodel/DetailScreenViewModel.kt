package com.example.moviesmobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.repo.MovieRepository
import com.example.moviesmobile.util.MovieDescriptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    private val _description = MutableStateFlow<String>("")
    val description: StateFlow<String> = _description

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            try {
                val movie = movieRepository.getMovieById(movieId)
                _movie.value = movie
                
                // Film yüklendiğinde açıklamayı güncelle
                movie?.let {
                    _description.value = MovieDescriptions.getDescription(
                        movieName = it.name,
                        director = it.director,
                        year = it.year
                    )
                }
            } catch (e: Exception) {
                // Hata durumunda işlemler
            }
        }
    }
} 