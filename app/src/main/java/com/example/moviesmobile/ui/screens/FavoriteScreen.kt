package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.data.entity.Movie
import com.example.moviesmobile.data.entity.FavoriteMovie
import com.example.moviesmobile.ui.components.*
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteScreenViewModel
) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var isRemoving by remember { mutableStateOf(false) }

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
                    itemsIndexed(
                        items = favoriteMovies,
                        key = { _, movie -> movie.movieId }
                    ) { index, favoriteMovie ->
                        val movie = favoriteMovie.toMovie()
                        FavoriteMovieCard(
                            movie = movie,
                            onMovieClick = { 
                                navController.navigate("detailScreen/${movie.id}") 
                            },
                            onRemoveClick = {
                                if (!isRemoving) {
                                    isRemoving = true
                                    coroutineScope.launch {
                                        viewModel.toggleFavorite(movie)
                                        isRemoving = false
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun FavoriteMovie.toMovie() = Movie(
    id = movieId,
    name = name,
    image = image,
    category = category,
    price = price,
    rating = rating,
    year = year,
    director = director,
    description = description
)

