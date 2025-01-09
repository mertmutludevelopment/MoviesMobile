package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.constants.Categories
import com.example.moviesmobile.ui.components.*
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    val movies by mainScreenViewModel.movies.collectAsState()
    val listState = rememberLazyListState()
    
    val firstVisibleItemOffset = remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset.toFloat()
        }
    }

    val filteredMovies = movies.filter { movie ->
        val matchesSearch = if (searchQuery.isEmpty()) {
            true
        } else {
            movie.name.contains(searchQuery, ignoreCase = true)
        }

        val matchesCategory = if (selectedCategory == "All") {
            true
        } else {
            movie.category == selectedCategory
        }

        matchesSearch && matchesCategory
    }

    val featuredMovie = filteredMovies.maxByOrNull { it.year }
    val topRatedMovies = filteredMovies.sortedByDescending { it.rating }.take(8)

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

        CategoryFilter(
            categories = Categories.categoryList,
            selectedCategory = selectedCategory,
            onCategorySelect = { selectedCategory = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (searchQuery.isEmpty()) {
                featuredMovie?.let {
                    item {
                        FeaturedMovie(
                            movie = it,
                            onMovieClick = { 
                                navController.navigate("detailScreen/${it.id}")
                            },
                            scrollOffset = firstVisibleItemOffset.value
                        )
                    }
                }

                item {
                    TopRatedMovies(
                        movies = topRatedMovies,
                        onMovieClick = { movieId ->
                            navController.navigate("detailScreen/$movieId")
                        }
                    )
                }
            }

            item {
                DiscoverMovies(
                    movies = filteredMovies,
                    onMovieClick = { movieId ->
                        navController.navigate("detailScreen/$movieId")
                    }
                )
            }
        }
    }
}

