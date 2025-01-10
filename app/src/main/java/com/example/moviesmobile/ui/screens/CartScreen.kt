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
import com.example.moviesmobile.constants.AppConstants
import com.example.moviesmobile.ui.components.DiscountCodeInput
import androidx.activity.compose.BackHandler

// Shopping cart screen that displays cart items, total price and discount options
// Handles item removal, discount application and purchase completion
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartScreenViewModel
) {
    BackHandler {
        navController.popBackStack()
    }

    val cartItems by viewModel.cartItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showSuccess by viewModel.showSuccess.collectAsState()
    var isDiscountApplied by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCartItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Main content section - only show when not in success state
        if (!showSuccess) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CartTopBar(
                    navController = navController
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Show empty state or cart items list
                if (cartItems.isEmpty()) {
                    EmptyCart()
                } else {
                    // Cart items list section
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentPadding = PaddingValues(bottom = 16.dp)
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

                    // Bottom section with discount and total price
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Background)
                    ) {
                        Column {
                            // Discount code input section
                            DiscountCodeInput(
                                onDiscountApplied = { isDiscounted ->
                                    isDiscountApplied = isDiscounted
                                },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 16.dp)
                            )
                            
                            // Calculate total price with discount if applied
                            val originalPrice = cartItems.sumOf { it.price * it.orderAmount }.toDouble()
                            val finalPrice = if (isDiscountApplied) {
                                originalPrice * (1 - AppConstants.DISCOUNT_PERCENTAGE)
                            } else {
                                originalPrice
                            }
                            
                            TotalPriceCard(
                                totalPrice = finalPrice,
                                onClick = { viewModel.clearAllCartItems() },
                                isDiscounted = isDiscountApplied
                            )
                        }
                    }
                }
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


