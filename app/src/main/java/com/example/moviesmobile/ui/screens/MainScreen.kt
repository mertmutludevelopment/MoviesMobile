package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.MainSearchBar
import com.example.moviesmobile.ui.components.MainTopBar
import com.example.moviesmobile.ui.components.MovieCard
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val movies by mainScreenViewModel.movies.collectAsState()

    val filteredMovies = if (searchQuery.isEmpty()) {
        movies
    } else {
        movies.filter { movie ->
            movie.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        MainTopBar(navController = navController)

        MainSearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredMovies) { movie ->
                MovieCard(
                    movie = movie,
                    onMovieClick = { 
                        navController.navigate("detailScreen/${movie.id}")
                    }
                )
            }
        }
    }
}

