package com.example.moviesmobile.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.*
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import com.example.moviesmobile.constants.AppConstants
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.unit.dp
import com.example.moviesmobile.constants.Reviews
import com.example.moviesmobile.constants.MovieCrewData
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import androidx.compose.ui.graphics.graphicsLayer

// Movie detail screen displaying comprehensive movie information
// Includes movie poster, details, crew info, reviews and purchase options
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel,
    favoriteViewModel: FavoriteScreenViewModel,
    movieId: Int
) {
    val movie by viewModel.movie.collectAsState()
    val description by viewModel.description.collectAsState()
    var showOrderDialog by remember { mutableStateOf(false) }
    var selectedAmount by remember { mutableStateOf(1) }
    
    // Scroll state for parallax effect and button visibility
    val scrollState = rememberScrollState()
    val buttonsAlpha by remember {
        derivedStateOf {
            if (scrollState.value > 150) 0f else (150 - scrollState.value) / 150f
        }
    }

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Scrollable content section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .verticalScroll(scrollState)
        ) {
            // Movie poster section
            Box {
                movie?.let { currentMovie ->
                    MovieDetailImage(
                        imageUrl = AppConstants.IMAGE_URL + currentMovie.image
                    )
                }
            }

            // Movie information sections
            movie?.let { currentMovie ->
                // Header with title, category, year and rating
                MovieDetailHeader(
                    movie = currentMovie
                )

                // Movie plot and description
                MovieDescription(
                    description = description,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Cast and crew information
                MovieCrewData.crewInfo[currentMovie.name]?.let { crew ->
                    MovieCrewSection(
                        crew = crew,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                
                // User reviews section
                MovieReviewSection(
                    reviews = Reviews.movieReviews,
                    modifier = Modifier.padding(bottom = 80.dp)
                )
            }
        }

        // Top bar with back button and favorite toggle
        DetailTopBar(
            navController = navController,
            movie = movie ?: return,
            favoriteViewModel = favoriteViewModel,
            modifier = Modifier.align(Alignment.TopStart),
            alpha = buttonsAlpha
        )

        // Bottom purchase button
        PurchaseButton(
            price = movie?.price?.toDouble() ?: 0.0,
            onClick = { showOrderDialog = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )

        // Ticket quantity selection dialog
        OrderAmountDialog(
            showDialog = showOrderDialog,
            selectedAmount = selectedAmount,
            price = movie?.price?.toDouble() ?: 0.0,
            onAmountChange = { selectedAmount = it },
            onConfirm = {
                viewModel.addToCart(selectedAmount)
                showOrderDialog = false
            },
            onDismiss = { showOrderDialog = false }
        )
    }
}

