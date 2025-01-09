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

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel,
    movieId: Int
) {
    val movie by viewModel.movie.collectAsState()
    val description by viewModel.description.collectAsState()
    var showOrderDialog by remember { mutableStateOf(false) }
    var selectedAmount by remember { mutableStateOf(1) }

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                movie?.let { currentMovie ->
                    MovieDetailImage(
                        imageUrl = AppConstants.IMAGE_URL + currentMovie.image
                    )
                }
            }

            movie?.let { currentMovie ->
                MovieDetailHeader(
                    movie = currentMovie
                )
                
                MovieDescription(
                    description = description,
                    modifier = Modifier.padding(bottom = 100.dp)
                )
            }
        }

        DetailTopBar(
            navController = navController,
            modifier = Modifier.align(Alignment.TopStart)
        )

        PurchaseButton(
            price = movie?.price?.toDouble() ?: 0.0,
            onClick = { showOrderDialog = true },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )

        OrderAmountDialog(
            showDialog = showOrderDialog,
            selectedAmount = selectedAmount,
            onAmountChange = { selectedAmount = it },
            onConfirm = {
                viewModel.addToCart(selectedAmount)
                showOrderDialog = false
            },
            onDismiss = { showOrderDialog = false }
        )
    }
}

