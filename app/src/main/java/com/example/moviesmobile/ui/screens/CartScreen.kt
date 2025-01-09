package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.CartItem
import com.example.moviesmobile.ui.components.CartTopBar
import com.example.moviesmobile.ui.components.EmptyCart
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartScreenViewModel
) {
    val cartItems by viewModel.cartItems.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCartItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        CartTopBar(
            title = "Shopping Cart",
            navController = navController
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (cartItems.isEmpty()) {
            EmptyCart()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(cartItems) { movie ->
                    CartItem(
                        movie = movie,
                        onDeleteClick = {
                            viewModel.deleteMovie(movie.cartId)
                        }
                    )
                }
            }
        }
    }
}

