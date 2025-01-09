package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.constants.Categories
import com.example.moviesmobile.ui.components.CategoryFilter
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
    var selectedCategory by remember { mutableStateOf("All") }
    val movies by mainScreenViewModel.movies.collectAsState()

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

        Spacer(modifier = Modifier.height(10.dp))

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

