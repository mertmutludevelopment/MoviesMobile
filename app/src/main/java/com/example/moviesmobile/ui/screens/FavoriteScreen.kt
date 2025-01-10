package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.ui.components.*
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteScreenViewModel
) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            FavoriteTopBar(
                onBackClick = { navController.popBackStack() }
            )

            if (favoriteMovies.isEmpty()) {
                EmptyFavorites()
            } else {
                FavoriteMoviesList(
                    movies = favoriteMovies,
                    onMovieClick = { movieId -> 
                        navController.navigate("detailScreen/$movieId") 
                    },
                    onRemoveClick = { movie ->
                        viewModel.removeFromFavorites(movie)
                    }
                )
            }
        }
    }
}

@Composable
private fun FavoriteMoviesList(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onRemoveClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 80.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            FavoriteMovieCard(
                movie = movie,
                onMovieClick = { onMovieClick(movie.id) },
                onRemoveClick = { onRemoveClick(movie) }
            )
        }
    }
}

