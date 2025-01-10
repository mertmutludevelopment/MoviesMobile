package com.example.moviesmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.moviesmobile.ui.screens.ScreenNavigation
import com.example.moviesmobile.ui.theme.MoviesMobileTheme
import com.example.moviesmobile.ui.viewmodel.CartScreenViewModel
import com.example.moviesmobile.ui.viewmodel.DetailScreenViewModel
import com.example.moviesmobile.ui.viewmodel.FavoriteScreenViewModel
import com.example.moviesmobile.ui.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

// Main entry point of the application
// Initializes ViewModels and sets up navigation
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModels 
    private val mainScreenViewModel: MainScreenViewModel by viewModels()
    private val detailScreenViewModel: DetailScreenViewModel by viewModels()
    private val cartScreenViewModel: CartScreenViewModel by viewModels()
    private val favoriteScreenViewModel: FavoriteScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesMobileTheme {
                // Set up navigation with required ViewModels
                ScreenNavigation(
                    mainScreenViewModel = mainScreenViewModel,
                    detailScreenViewModel = detailScreenViewModel,
                    cartScreenViewModel = cartScreenViewModel,
                    favoriteScreenViewModel = favoriteScreenViewModel
                )
            }
        }
    }
}


