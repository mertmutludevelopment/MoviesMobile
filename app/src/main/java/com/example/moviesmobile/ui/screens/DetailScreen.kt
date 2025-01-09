package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.*
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel

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

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        DetailTopBar(
            title = movie?.name ?: "",
            navController = navController
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MovieDetailContent(
                movie = movie,
                description = description
            )

            PurchaseButton(
                onClick = { showOrderDialog = true },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

