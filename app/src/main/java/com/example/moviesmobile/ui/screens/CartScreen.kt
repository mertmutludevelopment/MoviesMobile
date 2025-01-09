package com.example.moviesmobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesmobile.ui.components.CartItem
import com.example.moviesmobile.ui.components.CartTopBar
import com.example.moviesmobile.ui.components.EmptyCart
import com.example.moviesmobile.ui.components.TotalPriceCard
import com.example.moviesmobile.ui.components.SuccessMessage
import com.example.moviesmobile.ui.theme.Background
import com.example.moviesmobile.ui.theme.Primary
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartScreenViewModel
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showSuccess by viewModel.showSuccess.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCartItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        if (!showSuccess) {
            Column(
                modifier = Modifier.fillMaxSize()
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
                            .weight(1f)
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

            if (cartItems.isNotEmpty()) {
                val totalPrice = cartItems.sumOf { it.price * it.orderAmount }
                
                TotalPriceCard(
                    totalPrice = totalPrice.toDouble(),
                    onClick = { viewModel.clearAllCartItems() },
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Primary,
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        SuccessMessage(
            visible = showSuccess,
            modifier = Modifier.fillMaxSize()
        )
    }
}


